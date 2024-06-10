package com.xyzcorp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class EssentialFunctionsSpec extends AnyFunSpec with Matchers {
    describe("Map - which applies a function to every element it contains") {
        it("is in a list") {
            List(1, 2, 3).map(x => s"${x * 2}") should be(List("2", "4", "6"))
        }
        it("is in a Set") {
            Set(1, 2, 3).map(x => x * 2) should be(Set(2, 4, 6))
        }
        it("is in a Map") {
            val mapAsCollection = Map(1 -> "One", 2 -> "Two", 3 -> "Three")
            val result = mapAsCollection.map { (i, v) => (i + 1) -> (v + "!") }
            println(result)
        }
        it("is in Option") {
            val maybeInt = Option(20).map(x => x * 4)
            maybeInt should be (Option(80))
        }
    }
    describe("""Filter - which applies a function to removes
          | all elements that don't satisfy the predicate""".stripMargin) {
        it("is in List") {
            List(1,2,3).filter(x => x % 2 == 0) should be (List(2))
        }
        it("is in Option") {
            val result = Option(23).filter(x => x % 2 == 0)
            result should be (empty)
        }
    }
    describe("For Each - iterates all elements and performs a side effect") {
        it("is available for list") {
            List(1,2,3,4).foreach(println)
            println("---")
            List.empty[Int].foreach(println)
        }
    }
    describe("GroupBy - takes elements and groups by a function") {
        it("is available for list") {
            val result = List(1, 2, 3, 4)
                .groupBy(i => if i % 2 == 0 then "Even" else "Odd")
                .view
                .mapValues(xs => xs.length)
                .toMap
            result.apply("Odd") should be (2)
        }
    }
}
