package com.runbam.testoftests


import org.junit._
import Assert._
import org.hamcrest.Matchers._
import org.junit.Test

@Test
class TweetTest {
  @Test
  def testTweetCreate = {
    val t1 = new Tweet("author", "message")
    assertThat(t1.author, is("author"))
    assertThat(t1.body, is("message"))
    assertThat(t1.toString, is("@author: message"))
  }
}