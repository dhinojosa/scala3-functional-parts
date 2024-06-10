package com.xyzcorp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class FoldingSpec extends AnyFunSpec with Matchers {
    describe("Folding and Reduce"):
        describe("fold combines all the elements with a seed"):
            it("is available for list"):
                val result = List(1, 2, 3, 4, 5).fold(1) { (total, next) =>
                    println(s"total: $total\tnext: $next")
                    total * next
                }
                result should be(120)
            it("can be used for factorial"):

                def factorial(xs: List[Int]) = {
                    xs.fold(1) { (total, next) =>
                        println(s"total: $total\tnext: $next")
                        total * next
                    }
                }

                factorial(List(1, 2, 3, 4, 5)) should be(120)
            it("is available for set, but it may be out of order"):
                val result = Set(1, 2, 3, 4, 5).fold(1) { (total, next) =>
                    println(s"total: $total\tnext: $next")
                    total * next
                }
                result should be(120)
            it("is available for map, but that too may be order with key-value pairs"):
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

                val result = mapFromOneToTen.fold(0 -> "Zero") { case ((totalKey, totalValue), (nextKey, nextValue)) =>
                    totalKey + nextKey -> s"$totalValue, $nextValue"
                }
                result._1 should be(55)
                result._2 should include("Ten")
            it(
                """is different for Option as you have seen previously;
                  | if it has a value great, if it doesn't, perform a transform""".stripMargin):
                val result = Option(20).fold("Empty!")(x => s"$x!")
                result should be("20!")
            it(
                """is different for Option as you have not seen previously;
                  | It is beneficial when testing""".stripMargin):
                val result = Option(20).fold(fail("Did not expect the option to be empty"))(x => x should be(20))
        describe("foldLeft combines all the elements with a seed, it is the default when you call fold"):
            it("is available for list"):
                val result = List(1, 2, 3, 4, 5).foldLeft(1) { (total, next) =>
                    println(s"total: $total\tnext: $next")
                    total * next
                }
                result should be(120)
        describe(
            """foldRight combines all the elements with a seed, but it goes oppositely.
              |  Depending on the operation, you may choose to perform
              |  a right-hand side evaluation. One such case is evaluating
              |  recursive structures""".stripMargin):
            it("is available for list"):
                note("The next element is the first in the function")
                val result = List(1, 2, 3, 4, 5).foldRight(1) { (next, total) =>
                    println(s"total: $total\tnext: $next")
                    total * next
                }
                result should be(120)
        describe(
            """Reduce combines all the elements without a seed.
              |  It will select the leftmost as the seed for
              |  reduceLeft and reduce. It will get the
              |  rightmost for reduceRight""".stripMargin
        ):
            it("is available in List"):
                val result = List(1, 2, 3, 4, 5).reduce { (total, next) =>
                    println(s"total: $total\tnext: $next")
                    total * next
                }
                result should be(120)
        describe("Redoing the student average"):
            case class Grade(value: Float):
                if value > 100.0f || value < 0.0f then throw new IllegalArgumentException(s"$value is an invalid grade")

            note("this functional style has error handling built in, aka no entries")

            def calculateStudentAverage(grades: Grade*): Option[Grade] =
                grades
                    .map(grade  => grade.value)
                    .reduceLeftOption((total, next) => total + next)
                    .map(sum => Grade(sum / grades.length))

            val goodStudentResult = calculateStudentAverage(Grade(100), Grade(80), Grade(81), Grade(92))
            goodStudentResult should be(Some(Grade(88.25f)))

            val newStudentResult = calculateStudentAverage()
            newStudentResult should be(None)

            val badStudentResult = calculateStudentAverage(Grade(0), Grade(0), Grade(0), Grade(0))
            badStudentResult should be(Some(Grade(0.0)))
}
