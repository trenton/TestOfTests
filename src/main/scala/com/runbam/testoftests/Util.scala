package com.runbam.testoftests


object Util {
  /**
   * Reflect returns whatever is passed into it.
   */
  def reflect[T](x: T): T = {
    x
  }

  /**
   * Returns x * -1 where that's a defined and sane thing, otherwise,
   * throws a IllegalArgumentException*
   */
  def negate[T >: AnyVal](x: T): T = {
    x match {
      case y: Short => y * -1
      case y: Int => y * -1
      case y: Long => y * -1
      case y: Float => y * -1
      case y: Double => y * -1
      case y: Byte => (y * -1).toByte
      case _ => throw new IllegalArgumentException("Don't know how to negate objects of that type")
    }
  }

  /**
   * Repeats the string s times. 
   */
  def repeat(s: String, times: Int): String = {
    (0 until times).foldLeft("")((buf, _) => buf + s)
  }
}