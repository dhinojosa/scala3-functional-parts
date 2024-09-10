package com.xyzcorp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class CollectionsSpec extends AnyFunSpec with Matchers {
    describe("collections in Scala"):
        describe("a list"):
            it("can be created like the following"):
                val xs = List(1, 2, 3, 4)
                xs.length should be(4)
            it("can be created with right-associative colons"):
                val xs = 1 :: 2 :: 3 :: 4 :: Nil
                xs.length should be(4)
        describe("a set"):
            it("has unique elements"):
                val ys = Set("Hello", "Brown", "Character", "Howie", "Brown")
                val result = ys.contains("Character")
                result should be(true)
                println(ys)
                ys.size should be(4)
            it("uses hashcode of the objects, and mutability will make things inconsistent"):
                case class Country(var name: String, var capital: String)
                val nigeria = Country("Nigeria", "Abuja")
                val algeria = Country("Algeria", "Algiers")
                val egypt = Country("Egypt", "Cairo")
                val southAfrica = Country("South Africa", "Pretoria")
                val ethiopia = Country("Ethiopia", "Addis Ababa")
                val africaCountries = Set(
                    nigeria,
                    algeria,
                    egypt,
                    southAfrica,
                    ethiopia
                )
                africaCountries.size should be(5)
//              Uncomment the following and  see the results of doing so.
//                nigeria.name = "Ethiopia"
//                nigeria.capital = "Addis Ababa"
//                africaCountries.size should be(4)
        describe("a map"):
            it("is a sequence of associative pairs"):
                val map = Map("Norway" -> "Oslo", "Ghana" -> "Accra", "Chile" -> "Santiago")
                val result = map("Norway")
                result should be("Oslo")
            it("has a get or else method in case key is not found"):
                val map = Map("Norway" -> "Oslo", "Ghana" -> "Accra", "Chile" -> "Santiago")
                val result = map.getOrElse("Mongolia", "None")
                result should be("None")
        describe("an option") {
            it("is a type that represents if it has a valid value") {
                val o = Option(10)
                o should contain(10)
            }
            it("is a type that represents if it has also an invalid value") {
                val o = Option.empty[Int]
                o should be(empty)
            }
            it(
                """can be used everywhere you believe there is
                  | a chance that there wouldn't be a viable chance
                  | In the following example, you will see that there
                  | is a possibility a student will get a zero on the first
                  | day of class with no grades and that's discouraging""".stripMargin):
                case class Grade(value: Float):
                    if value > 100.0f || value < 0.0f then throw new IllegalArgumentException(s"$value is an invalid grade")

                def calculateStudentAverage(grades: Grade*): Option[Grade] =
                    var total = 0.0f
                    var sum = 0.0f
                    for grade <- grades do
                        sum += grade.value
                        total += 1
                    if total == 0 then Option.empty[Grade] // Can we change this to None?
                    else Option[Grade](Grade(sum / total)) // Can we change this to Some?

                val result = calculateStudentAverage(Grade(100), Grade(80), Grade(81), Grade(92))
                result should be(Some(Grade(88.25f)))
            describe("useful methods in collections") {
                it("has apply to get the element from a list") {
                    val list = List(2, 4, 6)
                    list(2) should be(6)
                }
                it("has mkString to produce a nice string for a collection") {
                    val list = List(2, 4, 6)
                    list.mkString(", ") should be("2, 4, 6")
                    list.mkString("[", ", ", "]") should be("[2, 4, 6]")
                }
                it("has head which is the first element of a collection") {
                    val list = List(2, 4, 6)
                    list.head should be(2)
                }
                it("has headOption which is the first element of a collection safely") {
                    val list = List(2, 4, 6)
                    list.headOption should be(Some(2))
                }
                it("contains subsetOf which will answer a boolean of whether one small set is included in another"):
                    Set(1, 2, 3).subsetOf(Set(0, 1, 2, 3, 4)) should be(true)
                it("contains intersection which are the common elements in both sets"):
                    Set(1, 2, 3, 4).intersect(Set(3, 4, 5, 6, 7)) should be(Set(3, 4))
                it("contains union which are the elements from both sets"):
                    Set(1, 2, 3, 4).union(Set(3, 4, 5, 6, 7)) should be(Set(1, 2, 3, 4, 5, 6, 7))
                it("contains diff which contains the difference of the left from the right"):
                    Set(1, 2, 3, 4).diff(Set(3, 4, 5, 6, 7)) should be(Set(1, 2))
            }
        }
}
