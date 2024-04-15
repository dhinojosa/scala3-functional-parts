package com.xyzcorp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class UnitSpec extends AnyFunSpec with Matchers {
  describe("a unit is a representation of void signifying you are not getting anything"):
    it("is done using ()"):
        val u: Unit = ()
        u should be (())
        u shouldBe a[Unit]
    it("""is primarily used as a return type in a method
          | This next method will have three printlns,
          | and every println will return a Unit, since the last line
          | is a println, that makes the entire method a Unit""".stripMargin):
        def doSomething(x:Int):Unit =
            println(x + 1)
            println(x + 2)
            println(x + 3)
        note("I am not making an assertion here because it didn't return anything")
        note("also this is a side effect")
        doSomething(30) should be (())
}
