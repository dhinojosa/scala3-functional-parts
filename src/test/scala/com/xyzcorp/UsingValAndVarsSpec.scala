package com.xyzcorp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class UsingValAndVarsSpec extends AnyFunSpec with Matchers {
    describe("a simple canary test") {
        it("should prove something simple like 1 should be 1") {
          1 should be (1)
        }
    }
    describe("a var") {
        it("is mutable") {
            var x = 10
            x = 20
            x should be (20)
        }
    }

    describe("a val") {
        it("is immutable") {
            val x = 10
            "x = 20" shouldNot compile
            x should be (10)
        }
    }

    describe("a lazy val") {
        it("is unresolved until requested") {
            lazy val x = {println("Loading the value"); 10}
            println("This statement should come up first")
            val result = x + 20
            result should be (30)
        }
    }
}
