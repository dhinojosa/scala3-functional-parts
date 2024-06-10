package com.xyzcorp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class MonoidSpec extends AnyFunSpec with Matchers {
    trait MyMonoid[A]:
        def combine(a: A, b: A): A

        def zero: A

    describe(
        """a monoid is a type class that defines how things combine together, and a zero
          |  which are implementations for what a zero means for the parameterized type""".stripMargin):
        it("can defined as a string"):
            given MyMonoid[Int] with
                override def combine(a: Int, b: Int): Int = a + b

                override def zero: Int = 0
            val monoidInt = summon[MyMonoid[Int]]
            val result = monoidInt.combine(10, 30)
            result should be(40)

            monoidInt.zero should be(0)
        it("can be defined for a Option"):
            note(
                """We need a Monoid for A so we can know how to combine them.
                  |  Typically, when we use using, we will typically use a capital letter,
                  |  like M""".stripMargin)

            given MyMonoid[Int] with
                override def combine(a: Int, b: Int): Int = a + b

                override def zero: Int = 0

            given optionMonoid[A](using M: MyMonoid[A]): MyMonoid[Option[A]] with
                override def combine(a: Option[A], b: Option[A]): Option[A] =
                    a.flatMap(x => b.map(y => M.combine(x, y)))

                override def zero: Option[A] = Option.empty[A]

            val result = summon[MyMonoid[Option[Int]]].combine(Option(13), Option(20))
            result should be(Option(33))
}
