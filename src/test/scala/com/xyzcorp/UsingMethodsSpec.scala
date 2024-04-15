package com.xyzcorp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class UsingMethodsSpec extends AnyFunSpec with Matchers {
   def isEven(i: Int): Boolean = i % 2 == 0
   
   describe("isEven") {
       it("should return false if given a 1") {
           val result:Boolean = isEven(1)
           result should be (false)
       }
       it("should return true if given a 2") {
           val result: Boolean = isEven(2)
           result should be (true)
       }
   }
}
