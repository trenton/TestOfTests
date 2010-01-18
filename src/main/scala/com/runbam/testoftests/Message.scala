package com.runbam.testoftests


class Message(val author: String, val body: String) {
  // max message size is 140
  require(body.length <= Message.MAX_BODY_LENGTH, "body too big")

  // do not allow empty messages
  require(body.length > 0, "body empty")

  override def toString = "@%s: %s".format(author, body)
}

object Message {
  val MAX_BODY_LENGTH = 140
}