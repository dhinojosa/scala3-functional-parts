package com.xyzcorp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class TraitsSpec extends AnyFunSpec with Matchers {
    describe(
        """A trait is analogous to an interface in Java. Classes and
          | objects can extend traits""".stripMargin):
        trait Vehicle:
            def increaseSpeed(mh: Int): Vehicle

            def decreaseSpeed(mh: Int): Vehicle

            def currentSpeedMetersPerHour: Int


        trait FunFactor:
            def funFactor: Int

        class Bicycle(val currentSpeedMetersPerHour: Int) extends Vehicle with FunFactor:
            override def increaseSpeed(mh: Int): Vehicle =
                new Bicycle(currentSpeedMetersPerHour + mh)

            override def decreaseSpeed(mh: Int): Vehicle =
                new Bicycle(currentSpeedMetersPerHour - mh)

            override def funFactor: Int = 10

        it("is then considered in the same relation as the trait, therefore having the same methods"):
            val result = new Bicycle(1)
                .increaseSpeed(3)
                .decreaseSpeed(1)
                .currentSpeedMetersPerHour
            result should be(3)

        it("any instance of the trait is polymorphic with the trait"):
            val bicycle: Bicycle = new Bicycle(1)
            val vehicle: Vehicle = new Bicycle(3)
            val anyRef: AnyRef = new Bicycle(5)
            val any: Any = new Bicycle(5)
            bicycle shouldBe a[Any]
}
