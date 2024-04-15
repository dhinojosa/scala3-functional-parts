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
            it ("has unique elements"):
                val ys = Set("Hello", "Brown", "Character", "Howie", "Brown")
                val result = ys.contains("Character")
                result should be(true)
                println(ys)
                ys.size should be(4)
            it("uses hashcode of the objects, and mutability will make things inconsistent"):
                case class Country(var name:String, var capital:String)
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

                nigeria.name = "Ethiopia"
                nigeria.capital = "Addis Ababa"

                africaCountries.size should be (4)
        describe("a map"):
            it("is a sequence of associative pairs"):
                val map = Map("Norway" -> "Oslo", "Ghana" -> "Accra", "Chile" -> "Santiago")
                val result = map("Norway")
                result should be ("Oslo")
            it("has a get or else method in case key is not found"):
                val map = Map("Norway" -> "Oslo", "Ghana" -> "Accra", "Chile" -> "Santiago")
                val result = map.getOrElse("Mongolia", "None")
                result should be("None")
}
