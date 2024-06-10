package com.xyzcorp

object Conversions:
  given Conversion[Fahrenheit, Celsius] with
    def apply(f: Fahrenheit): Celsius =
      Celsius(((f.value.toDouble - 32) * (5.0 / 9.0)).round.toInt)

  given Conversion[Celsius, Fahrenheit] with
    def apply(c: Celsius): Fahrenheit =
      Fahrenheit(((c.value.toDouble * (9.0 / 5.0)) + 32).round.toInt)