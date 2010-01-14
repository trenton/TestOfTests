package com.runbam.testoftests


object Util {
  /**
   * Reflect returns whatever is passed into it.
   */
  def reflect[T](x: T): T = {
    x
  }

  def negate[T >: AnyVal](x: T): T = {
    x match {
      case y: Int => y * -1
      case y: Long => y * -1
      case y: Float => y * -1
      case y: Double => y * -1
      case y: Short => y * -1
      case y: Byte => (y * -1).toByte
      case _ => throw new IllegalArgumentException("Don't know how to negate objects of that type")
    }
  }
}