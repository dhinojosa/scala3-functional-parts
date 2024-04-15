package com.xyzcorp

object LetterCounter {
   def countAllThe(c:Char):String => Int = (s:String) => s.count(cx => cx == c)
}
