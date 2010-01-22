package com.runbam.testoftests

import org.junit.Test
import org.specs._
import runner.JUnit4
import org.scalacheck.Prop.forAll
import util.DataTables

/**
 * http://code.google.com/p/specs/wiki/RunningSpecs
 */
class UtilSpecsTest extends JUnit4(UtilSpec, UtilAdvancedSpec)

object UtilSpec extends Specification {
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

    //scalacheck http://code.google.com/p/scalacheck/wiki/UserGuide
    //scalac gets confused without the semicolons
    forAll {n: Int => Util.negate(Util.negate(n)) == n} check;
    forAll {n: Long => Util.negate(Util.negate(n)) == n} check;
    forAll {n: Float => Util.negate(Util.negate(n)) == n} check;
  }

  "repeat(foo, 3) is foofoofoo" in {
    Util.repeat("foo", 0) must be equalTo("")
    Util.repeat("foo", 3) must be equalTo("foofoofoo")
    Util.repeat("foo", 0) must be matching("")
    Util.repeat("foo", 3) must be matching("^foofoofoo$")
    Util.repeat("foo", 3) must have size ("foo".size * 3)
  }
}

// http://code.google.com/p/specs/wiki/AdvancedSpecifications
// :TODO: not the best error message on failure, despite what docs show should happen
object UtilAdvancedSpec extends Specification with DataTables {
  "repeated repeat tests" in {
    "string" | "times" | "expected" |>
    "foo"    ! 0       ! ""         |
    "foo"    ! 1       ! "foo"      |
    "foo"    ! 2       ! "foofoo"   |
    "foo "   ! 2       ! "foo foo " | 
            {(str, times, expectation) =>
      Util.repeat(str, times) must be equalTo(expectation)
    }
  }
}