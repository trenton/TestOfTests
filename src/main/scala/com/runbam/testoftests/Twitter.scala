package com.runbam.testoftests


trait Twitter {
  def post(post: Tweet)
  def getFeed(start: Int, end: Int): List[Tweet]
}