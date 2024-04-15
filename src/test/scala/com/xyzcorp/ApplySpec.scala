package com.xyzcorp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class ApplySpec extends AnyFunSpec with Matchers {
   describe("You can use any object with an implicit application"):
       it("can be used in a class"):
            class Foo(x:Int):
              def apply(y:Int):Int = x + y

            val foo = new Foo(3)
            val result = foo(2)
            result should be (5)
       it("can be used in an object"):
            object Boom:
               def apply(x:Int):Int = x + 3
            val result = Boom(20)
            result should be (23)

}
