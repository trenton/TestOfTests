package com.runbam.testoftests


import actors.Actor
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Example client for MessageService. Posts a message every n milliseconds
 * until shutdown is again. To shutdown, pass 'Shutdown or call shutdown
 */
class MessagePinger(val delayMs: Long, val service: MessageService) extends Actor {
  implicit def unboxAtomicBoolean(b: AtomicBoolean): Boolean = b.get

  implicit def boxAtomicBoolean(b: Boolean): AtomicBoolean = new AtomicBoolean(b)

  private val shutdownRequested: AtomicBoolean = false

  def shutdown: Unit = {
    shutdownRequested.set(true)
  }

  override def start: Actor = {
    val me = super.start
    me ! 'Ping
    me
  }

  /**
   * But the preferred method to create actors is through Actor.actor
   */
  def act() {
    while (!shutdownRequested) {
      receive {
        case 'Ping => {
          writePing
          Thread.sleep(delayMs)
          this ! 'Ping
        }
        case 'Shutdown => {
          shutdown
        }
        case _ => {
          Console.err.println("unknown message")
        }
      }
    }
  }

  def writePing() {
    service.post(new Message(MessagePinger.AUTHOR, "the time is: %s".format(System.currentTimeMillis)))

  }
}

object MessagePinger {
  val AUTHOR = "pinger"
}