package com.runbam.testoftests


import io.Source
import java.io.File
import org.junit._
import Assert._
import org.hamcrest.Matchers._
import org.junit.Test

@Test
class MessageJunitTest {
  @Test
  def testTweetCreate: Unit = {
    val t1 = new Message("author", "message")
    assertThat(t1.author, is("author"))
    assertThat(t1.body, is("message"))
    assertThat(t1.toString, is("@author: message"))

    val bigMessage = (0 until Message.MAX_BODY_LENGTH + 1).foldLeft("")(_ + _)
    try {
      new Message("author", bigMessage)
      fail("Long message should bugger us")
    }
    catch {
      case e: IllegalArgumentException => assertTrue(true)
      case _ => fail("Long message should bugger us")
    }

    try {
      new Message("author", "")
      fail("Empty message should bugger us")
    }
    catch {
      case e: IllegalArgumentException => assertTrue(true)
      case _ => fail("Empty message should bugger us")
    }
  }

  @Test
  def testTwitterEndToEnd: Unit = {
    val out: File = File.createTempFile("TwitterTest", ".txt")
    out.deleteOnExit

    val serviceToFile = new MessageServiceToFile(out)

    val msg: Message = new Message("user", "message")
    serviceToFile.post(msg)

    val outIterator: Iterator[String] = Source.fromFile(out).getLines
    val postFromFile = outIterator.next
    assertThat(postFromFile, startsWith(msg.toString))
    assertThat(outIterator.hasNext, is(false))
  }
}