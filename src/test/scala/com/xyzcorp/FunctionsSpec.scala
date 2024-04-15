package com.xyzcorp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import java.time.temporal.ChronoUnit
import java.time.{Duration, Instant}

class FunctionsSpec extends AnyFunSpec with Matchers{
   describe("functions"):
       it("is like a method but detached from a class or an object"):
           val addOne = (x:Int) => x + 1
           addOne(3) should be (4)
       it("can take on more than one argument"):
           val addTwoNumsAndOneReturnOne = (x:Int, y:Int) => x + y + 1
           addTwoNumsAndOneReturnOne(3, 10) should be (14)
       it("can be curried where each argument can be partially applied"):
           note("x will be closured here")
           val addTwoNumsAndOneCurried = (x:Int) => (y:Int) => x + y + 1
           val intToInt = addTwoNumsAndOneCurried(2)
           intToInt(4) should be (7)
       it("can be used as a return type to create an effect that we can inevitably continue"):
           val aCount: String => Int = LetterCounter.countAllThe('a')
           aCount("apple") should be (1)
           aCount("arizona") should be (2)
       it("can also be expressed as a thunk; officially called a by-name parameter"):
           def timed[A](f: => A): (Duration, A) =
               val before = Instant.now
               val result = f
               note("we are using a tuple to return more than one item")
               Duration.between(before, Instant.now) -> result
           val timedResult = timed {
               Thread.sleep(1000)
               "Hello"
           }
           timedResult._1.toMillis shouldBe > (1000L)
           timedResult._2 should be ("Hello")
}
