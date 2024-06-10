package com.xyzcorp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class ExtensionsSpec extends AnyFunSpec with Matchers {
    describe("an extension"):
        it("extends a method in any object. It is performed by an implicit definition"):
            note("This extension is in scope")
            extension (x: Int)
                def isOdd: Boolean = x % 2 != 0
                def isEven: Boolean = !isOdd

            10.isEven should be(true)
            13.isOdd should be(true)
    describe("an extension can be used with generic types"):
        it("Can apply or mold to any object that can be generic"):
            extension [T](xs: List[T])
                def second: Option[T] = xs match
                    case _ :: snd :: _ => Some(snd)
                    case _ => None

            List(2, 3, 4, 5).second should be(Option(3))
            List(2).second should be(None)
            List.empty[Int].second should be(None)
    describe("an extension imported from another package"):
        it("can be brought in using a given import"):
            import MyExtensions.*
            val result = "Please, play that song again".waveCase
            result should be("PlEaSe, PlAy tHaT SoNg aGaIn")
            "test".exclaim should be("test!")
}
