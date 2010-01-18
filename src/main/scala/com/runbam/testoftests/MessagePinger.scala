package com.runbam.testoftests


import actors.Actor
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Posts a message every n milliseconds until shutdown is again.
 */
class MessagePinger(val delayMs: Long, val service: MessageService) extends Actor {
  private val shutdownRequested = new AtomicBoolean(false)

  def shutdown: Unit = {
    shutdownRequested.set(true)

  }

  override def start(): Actor = {
    val me = super.start
    me ! 'Ping
    me
  }

  /**
   * But the preferred method to create actors is through Actor.actor
   */
  def act() {
    // :TODO: make an implicit for the get call
    while (!shutdownRequested.get) {
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
    service.post(new Message("pinger", "the time is: %s".format(System.currentTimeMillis)))

  }

  /*
  private def pinger(): Unit = {
    while(! shutdownRequested) {
      service.post(new Message("pinger", System.currentTimeMillis))
      

    }
  }
  */

}