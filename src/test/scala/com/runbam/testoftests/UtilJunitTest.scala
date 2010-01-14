package com.runbam.testoftests

import org.junit._
import Assert._
import org.hamcrest.Matchers._
import org.junit.Test


@Test
class UtilJunitTest {
  @Test
  def testReflect = {
    assertThat(Util.reflect(5), is(5))
    assertThat(Util.reflect("xyz"), is("xyz"))
    assertThat(Util.reflect(1234.56), is(1234.56))
  }

  @Test
  def testNegate = {
    assertEquals(Util.negate(1), -1)
    assertEquals(Util.negate(-1), 1)
    assertEquals(Util.negate(1.2f), -1.2f)

    try {
      assertEquals(Util.negate('x'), 'X')
      fail("Should have thrown exception for unknown type")
    }
    catch {
      case e: IllegalArgumentException => assertThat(e.getMessage, startsWith("Don't know how to negate "))
      case e => fail("Caught unexpected exception: " + e.getMessage)
    }
  }

  @Test
  def testRepeat() = {
    assertThat(Util.repeat("foo", 0), is(""))
    assertThat(Util.repeat("foo", 1), is("foo"))
    assertThat(Util.repeat("foo", 2), is("foofoo"))
    assertThat(Util.repeat("x", 5), is("xxxxx"))
  }
}