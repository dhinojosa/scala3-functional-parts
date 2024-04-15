package com.xyzcorp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class TuplesSpec extends AnyFunSpec with Matchers {
  describe("a tuple"):
     it("is a way to pair items, and you can retreive those elements with ._1, and ._2"):
        val tuple = (1, "Hello")
        tuple._1 should be (1)
        tuple._2 should be ("Hello")
     it("""can have as many element as you would like
           | to infinity, it used to be 22 before Scala 3""".stripMargin):
        val tuple = (1, "Hello", 40.0, 900L, BigInt(20))
        tuple._5 should be (BigInt(20))
     it("can use -> to express a tuple of 2, or a pair"):
        val tuple = 1 -> "One"
        tuple._1 should be (1)
        tuple._2 should be ("One")
}
