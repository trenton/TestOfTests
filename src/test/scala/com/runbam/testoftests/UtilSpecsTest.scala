package com.runbam.testoftests

import org.junit.Test
import org.specs._
import runner.JUnit4

/**
 * http://code.google.com/p/specs/wiki/RunningSpecs
 */
class UtilSpecsTest extends JUnit4(UtilSpec)

object UtilSpec extends Specification {
  "'hello world' has 11 characters" in {
    "hello world".size must_== 11
  }

  "'hello world' matches 'h.* w.*'" in {
    "hello world" must be matching ("h.* w.*")
  }
}