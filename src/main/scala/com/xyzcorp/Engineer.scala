package com.xyzcorp

case class Engineer(val firstName: String, val lastName: String){
    Engineer._count += 1
}

object Engineer {
    private var _count:Int = 0
    def count:Int = _count
}
