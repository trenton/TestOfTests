package com.runbam.testoftests


import io.Source
import java.io.File
import org.junit._
import Assert._
import org.hamcrest.Matchers._
import org.junit.Test

@Test
class TwitterTest {
  @Test
  def testTweetCreate: Unit = {
    val t1 = new Tweet("author", "message")
    assertThat(t1.author, is("author"))
    assertThat(t1.body, is("message"))
    assertThat(t1.toString, is("@author: message"))

    val bigMessage = (0 until Tweet.MAX_BODY_LENGTH + 1).foldLeft("")(_ + _)
    try {
      new Tweet("author", bigMessage)
      fail("Long message should bugger us")
    }
    catch {
      case e: IllegalArgumentException => assertTrue(true)
      case _ => fail("Long message should bugger us")
    }

    try {
      new Tweet("author", "")
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

    val twitterApi = new TwitterAsFile(out)

    val tweet: Tweet = new Tweet("user", "message")
    twitterApi.post(tweet)

    val outIterator: Iterator[String] = Source.fromFile(out).getLines
    val postFromFile = outIterator.next
    assertThat(postFromFile, startsWith(tweet.toString))
    assertThat(outIterator.hasNext, is(false))
  }
}