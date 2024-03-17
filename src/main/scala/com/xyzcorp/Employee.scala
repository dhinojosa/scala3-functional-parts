package com.xyzcorp

case class Employee(firstName:String, lastName:String)


@main
def createAndUseEmployee() = {
   val e = new Employee("Simon", "Simpson")
   println(e)
}
