package com.runbam.testoftests


/**
 * Twitter represents a simplistic view of the Twitter API. Implementations
 * of this interface would allow users to post new Tweets and get a feed of
 * Tweets from other users.
 * <p>
 * Note: any type of authentication is handled externally. This is just the
 * core set of API functions.
 */
trait Twitter {
  /**Send your Tweet to the world. */
  def post(post: Tweet): Unit

  /**
   * Performs "validation" on post, and if okay, returns it
   * otherwise throws exception with the troubles. Recommended,
   * but not required, to call this before post.
   */
  def validate(post: Tweet): Tweet

  /**
   * Get a list of Tweets the system thinks you'd like to see.
   */
  def getFeed(start: Int, end: Int): List[Tweet]
}