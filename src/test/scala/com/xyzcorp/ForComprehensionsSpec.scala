package com.xyzcorp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class ForComprehensionsSpec extends AnyFunSpec with Matchers {
    describe("A monadic composition is a design pattern"):
        it("is a common form. Notice the interwoven style of flatMap, flatMap, map"):
            val result =
                Option(3).flatMap(x =>
                    Option(4).flatMap(y =>
                        Option(10).map(z =>
                            x + y + z)))
            result should be(Option(17))
        it("has the the added effect of error handling, if any element in the chain is empty, it is entirely empty"):
            val result =
                Option(3).flatMap(x =>
                    Option.empty[Int].flatMap(y =>
                        Option(10).map(z => x + y + z)))
            result should be(empty)
        it("can be done with a List"):
            val result = List(1, 2, 3)
                .flatMap(x =>
                    List(-x, x, x + 1)
                        .flatMap(y =>
                            List(y, y * 2)
                                .map(z => x + y + z)
                        )
                )
            result should be(List(-1, -2, 3, 4, 5, 7, -2, -4, 6, 8, 8, 11, -3, -6, 9, 12, 11, 15))
        it("can be done with a List. Remember an empty list would be the error case"):
            val result = List(1, 2, 3)
                .flatMap(x =>
                    List
                        .empty[Int]
                        .flatMap(y =>
                            List(y, y * 2)
                                .map(z => x + y + z)
                        )
                )
            result should be(empty)
    describe("for comprehensions are for flatMap, flatMap*, map"):
        it("can be done with an option, compare and contrast with the previous section"):
            val result =
                for (
                    x <- Option(3);
                    y <- Option(4);
                    z <- Option(10)
                ) yield x + y + z
            result should be(Option(17))
        it("has a filter pattern meaning you can have an if statement in a line of your choice"):
            val result =
                for (
                    x <- Option(3);
                    y <- Option(4) if x < 2;
                    z <- Option(10)
                ) yield x + y + z
            result should be(None)
        it("you can also make assignments within the for comprehension"):
            val result =
                for (
                    x <- Option(3);
                    updated = x * 19;
                    y <- Option(updated) if x >= 2;
                    z <- Option(10)
                ) yield x + y + z
            result should be(Option(70))

}
