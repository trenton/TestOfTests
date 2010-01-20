package com.runbam.testoftests


import org.jmock.{Expectations, Mockery}
import org.junit._
import Assert._
import org.junit.Test
import org.jmock.Expectations._


/**
 * Inspired by http://lalitpant.blogspot.com/2007/12/using-jmock-with-scala.html
 */
class MessagePingerJunitTest {
  val context = new Mockery() {
    {
      // allows us to mock classes
      // adding specs 1.6 caused compile problem (perhaps it brings
      // a different version of jmock)
      //import org.jmock.lib.legacy.ClassImposteriser
      //setImposteriser(ClassImposteriser.INSTANCE);
    }
  };

  @Test
  def testLifecycle = {
    val msgService = (context.mock(classOf[MessageService])).asInstanceOf[MessageService]

    val expectedAuthor = "pinger"
    context.checking(
      new Expectations {
        exactly(2).of(msgService).post(`with`(any(classOf[Message])))
      })

    val delayMs = 2000
    val pinger = new MessagePinger(delayMs, msgService)
    pinger.start

    Thread.sleep(delayMs + 100)

    pinger.shutdown

    context.assertIsSatisfied
  }
}