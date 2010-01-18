package com.runbam.testoftests


import java.io.{FileWriter, File}

class MessageServiceToFile(out: File) extends MessageService {
  def getFeed(start: Int, end: Int) = {
    throw new UnsupportedOperationException
  }

  def validate(post: Message) = {
    throw new UnsupportedOperationException
  }

  def post(post: Message) = {
    val writer: FileWriter = new FileWriter(out, true)
    writer.write(post.toString)
    writer.write("\n")
    writer.close
  }
}