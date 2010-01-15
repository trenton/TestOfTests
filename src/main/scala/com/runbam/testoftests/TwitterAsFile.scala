package com.runbam.testoftests


import java.io.{FileWriter, File}

class TwitterAsFile(out: File) extends Twitter {
  def getFeed(start: Int, end: Int) = {
    throw new UnsupportedOperationException
  }

  def validate(post: Tweet) = {
    throw new UnsupportedOperationException
  }

  def post(post: Tweet) = {
    val writer: FileWriter = new FileWriter(out)
    writer.write(post.toString)
    writer.write("\n")
    writer.close
  }
}