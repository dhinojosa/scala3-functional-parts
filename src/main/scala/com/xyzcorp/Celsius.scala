package com.xyzcorp

case class Celsius(value: Int):
  override def toString = s"$value°C"