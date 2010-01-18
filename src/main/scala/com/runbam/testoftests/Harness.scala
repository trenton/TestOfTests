package com.runbam.testoftests


import java.io.File

object Harness {
  def main(args: Array[String]): Unit = {
    val pinger = new MessagePinger(123, buildService)

    pinger.start

    Thread.sleep(10 * 1000)

    pinger ! 'Shutdown
  }

  def buildService(): MessageService = {
    new MessageServiceToFile(new File("/var/tmp/msgsvc.out"))
  }
}