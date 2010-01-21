package com.runbam.testoftests


import org.specs.matcher.Matcher
import org.specs.mock.{JMocker, ClassMocker}
import org.specs.runner.JUnit4
import org.specs.Specification
import org.hamcrest.Matchers._
import org.junit.Assert._

class MessagePingerSpecsTest extends JUnit4(MessagePingerSpec)

case class MessageMatcher(val expectedAuthor: String) extends Matcher[Message] {
  // :TODO: understand =>Class syntax
  def apply(msg: =>Message) = {
    val success = "expected %s and saw %s".format(expectedAuthor, msg.author)
    val failure = "expected %s but saw %s".format(expectedAuthor, msg.author)
    (msg.author == expectedAuthor, success, failure)
  }
}

// http://code.google.com/p/specs/wiki/UsingJMock
object MessagePingerSpec extends Specification with JMocker with ClassMocker {
  "MessagePinger" should {
    "use mocks" in {
      val msgService = mock[MessageService]

      expect {
        val messageCapture = capturingParam[Message]
        // :TODO: message on failure says post failed to be called, not that the matcher failed
        // :TODO: can this be done without the helper class?
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
