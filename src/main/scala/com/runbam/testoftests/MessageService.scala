package com.runbam.testoftests


/**
 * MessageService represents a simplistic view of a message posting/getting service,
 * kinda like Twitter. Implementations of this interface would allow users to post
 * new Messages and get a feed of Messages from other users.
 * <p>
 * Note: any type of authentication is handled externally. This is just the
 * core set of API functions.
 */
trait MessageService {
  /**Send your Message to the world. */
  def post(post: Message): Unit

  /**
   * Performs "validation" on post, and if okay, returns it
   * otherwise throws exception with the troubles. Recommended,
   * but not required, to call this before post.
   */
  def validate(post: Message): Message

  /**
   * Get a list of Messages the system thinks you'd like to see.
   */
  def getFeed(start: Int, end: Int): List[Message]
}