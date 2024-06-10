package com.xyzcorp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class FlatMapSpec extends AnyFunSpec with Matchers {
    describe("flatMap"):
        describe("is the most essential of functions, but first the mechanics"):
            it("is available in a list"):
                val result = List(1, 2, 3, 4).flatMap(x => List(-x, x, x + 3))
                result should be(List(-1, 1, 4, -2, 2, 5, -3, 3, 6, -4, 4, 7))
            it("is available in a set"):
                alert("This may have some side effects due to the nature of sets")
                val result = Set(1, 2, 3, 4).flatMap(x => Set(-x, x, x + 3))
                result should be(Set(-1, 1, 4, -2, 2, 5, -3, 3, 6, -4, 4, 7))
            it("is available in a map returning an iterable"):
                val mapFromOneToTen = Map(
                    1 -> "One",
                    2 -> "Two",
                    3 -> "Three",
                    4 -> "Four",
                    5 -> "Five",
                    6 -> "Six",
                    7 -> "Seven",
                    8 -> "Eight",
                    9 -> "Nine",
                    10 -> "Ten"
                )
                info("notice though, that it has different semantics here; I need to return an Iterable (single, or tuple)")
                val result = mapFromOneToTen.flatMap((k, v) => List(v + ".", v + "!", v + "?"))
                println(result)
                result should have size 30
            it("is available in an option and this is one of the important aspects, but option is not a collection"):
                val maybeInt = Option(10).flatMap(x => Option(x * 2))
                maybeInt should be(Option(20))
        describe("has an interesting characteristic, it can be used to filter"):
            it("will remove any item of the same container if it is of an empty quality"):
                val list = List(1, 2, 5, 0, 10).flatMap { x =>
                    try {
                        List(100 / x)
                    } catch {
                        case e: ArithmeticException =>
                            List.empty[Int]
                    }
                }
                (list should contain).inOrder(100, 50, 20, 10)
        describe("a use case") {
            it("is highly useful in word counts") {
                val lyrics =
                    """I see trees of green red roses too
                      |I see them bloom for me and you
                      |And I think to myself what a wonderful world""".stripMargin
                val result = lyrics
                    .split(System.getProperty("line.separator"))
                    .flatMap(phrase => phrase.split("""\s+"""))
                    .map(word => word.toLowerCase)
                    .groupBy(s => s.head)
                    .view
                    .mapValues(xs => xs.length)
                println(result.toMap)
            }
        }
}
