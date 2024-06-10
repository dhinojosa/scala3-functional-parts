package com.xyzcorp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class FunctorsSpec extends AnyFunSpec with Matchers {
    describe("higher kinded type") {
        it("can be used to have one method for different collections or containers") {
            note("I want to change this method, nor do I want to make more methods")

            def method[F[_], A](xs: F[A]): Unit = println(xs)

            note("I do not want to change the call")
            method(List(1, 2, 3, 4))
            method(List("A", "B", "C", "D"))
            method(List('a', 'b', 'c', 'd'))
            method(Option(30))
        }
        describe("a functor is a typeclass that performs a map"):
            trait MyFunctor[M[_]]:
                def myMap[A, B](m: M[A])(f: A => B): M[B]

            it("can be used with a list since it has the shape F[A]"):
                note("Like with eq, and show, and ordering we can have different implementations on demand")
                given MyFunctor[List] with
                    override def myMap[A, B](m: List[A])(f: A => B): List[B] = m.map(f)
                val result = summon[MyFunctor[List]].myMap(List(1, 2, 3, 4))(x => x * 2)
                result should be(List(2, 4, 6, 8))
            it("can be used with an option since that too has the shape F[A]"):
                given MyFunctor[Option] with
                    override def myMap[A, B](m: Option[A])(f: A => B): Option[B] = m.map(f)
                val result = summon[MyFunctor[Option]].myMap(Option(30))(x => x * 2)
                result should be (Option(60))
    }
}
