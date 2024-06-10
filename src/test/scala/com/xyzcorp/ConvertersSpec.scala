package com.xyzcorp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class ConvertersSpec extends AnyFunSpec with Matchers {
    describe("A converter is one type can be converted to another on demand. "):
        it("performs conversions implicitly using a given"):
            import Conversions.given
            "0°C in Fahrenheit is %s".format(Celsius(0): Fahrenheit) should be("0°C in Fahrenheit is 32°F")
            "100°C in Fahrenheit is %s".format(Celsius(100): Fahrenheit) should be("100°C in Fahrenheit is 212°F")
            "100°F in Celsius is %s".format(Fahrenheit(100): Celsius) should be("100°F in Celsius is 38°C")
        it("takes place behind the scenes in Scala, consider the following addition"):
            val result = BigInt("30012993") + 12
            result should be(BigInt("30013005"))

}
