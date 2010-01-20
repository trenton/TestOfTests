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

  "negate(0) is 0" in {
    Util.negate(0) must_== 0
  }

  "negate(1) is -1" in {
    Util.negate(1) must_== -1
  }

  "negate(negate(n)) is n" in {
    val rand = new Random

    val n = rand.nextInt(2^31 - 1)
    Util.negate(n) must_== n * -1
    Util.negate(Util.negate(n)) must_== n
  }
}