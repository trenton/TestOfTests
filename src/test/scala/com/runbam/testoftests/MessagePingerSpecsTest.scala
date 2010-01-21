package com.runbam.testoftests


import org.specs.matcher.Matcher
import org.specs.mock.{JMocker, ClassMocker}
import org.specs.runner.JUnit4
import org.specs.Specification
import org.hamcrest.Matchers._
import org.junit.Assert._

class MessagePingerSpecsTest extends JUnit4(MessagePingerSpec)

// :TODO: is the () needed? wtf is that?
case class MessageMatcher(val expectedAuthor: String) extends Matcher[Message]() {
  def apply(msg: =>Message) = {
    val result: MatcherResult = (true, "okay", "nope")

    result
  }
}

// http://code.google.com/p/specs/wiki/UsingJMock
object MessagePingerSpec extends Specification with JMocker with ClassMocker {
  "MessagePinger" should {
    "use mocks" in {
      val msgService = mock[MessageService]

      expect {
        val messageCapture = capturingParam[Message]
        // :TODO: learn how to do this inline (without the class)
        exactly(2).of(msgService).post(new MessageMatcher(MessagePinger.AUTHOR))
      }

      val delayMs = 2000
      val pinger = new MessagePinger(delayMs, msgService)
      pinger.start

      Thread.sleep(delayMs + 100)

      pinger.shutdown
    }
  }
}
