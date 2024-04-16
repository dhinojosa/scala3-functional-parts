package com.xyzcorp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class ObjectsSpec extends AnyFunSpec with Matchers {
    describe("an object") {
        it("shares the same object") {
            val ref1 = MySingleton
            val ref2 = MySingleton
            (ref1 eq ref2) should be(true)
            (ref1 == ref2) should be(true)
        }
        it("can contain a method") {
            MySingleton.add(30, 10) should be(40)
        }
    }
}
