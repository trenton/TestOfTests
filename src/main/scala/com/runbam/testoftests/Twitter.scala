package com.runbam.testoftests


trait Twitter {
  def post(post: Tweet): Unit

  // performs "validation" on post, and if okay, returns it
  // otherwise throws exception with the troubles
  def validate(post: Tweet): Tweet

  def getFeed(start: Int, end: Int): List[Tweet]
}