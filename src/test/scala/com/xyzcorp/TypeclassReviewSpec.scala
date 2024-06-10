package com.xyzcorp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.language.postfixOps

class TypeclassReviewSpec extends AnyFunSpec with Matchers:

    trait MyFunctor[F[_]]:
        def map[A, B](fa: F[A])(f: A => B): F[B]

    trait MyMonad[F[_]] extends MyFunctor[F]:
        def pure[A](a: A): F[A]

        def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]

        override def map[A, B](fa: F[A])(f: A => B): F[B] = flatMap(fa)(a => pure(f(a)))

    trait MyMonoid[A]:
        def empty: A

        def combine(x: A, y: A): A

    note("""all the these definitions can be hidden away""")

    given MyMonad[List] with
        override def map[A, B](m: List[A])(f: A => B): List[B] = m.map(f)

        override def flatMap[A, B](fa: List[A])(f: A => List[B]): List[B] = fa.flatMap(f)

        override def pure[A](a: A): List[A] = List(a)

    given MyMonad[Option] with
        override def map[A, B](m: Option[A])(f: A => B): Option[B] = m.map(f)

        override def flatMap[A, B](fa: Option[A])(f: A => Option[B]): Option[B] = fa.flatMap(f)

        override def pure[A](a: A): Option[A] = Option(a)

    given MyMonoid[Int] with
        override def empty: Int = 0

        override def combine(x: Int, y: Int): Int = x + y


    def process[F[_], A](container: F[A])(using M: MyMonoid[A], F: MyMonad[F]): F[A] =
        F.map(container)(a => M.combine(a, a))

    describe("What was the point?"):
        it(
            """allows you to create one method to rule them all! That's the point! In other
              | words the above method can be used by Option, or a List since both of those containers
              | have monoid implementations and functor. Conclusion.
              | This is a style and it is an advanced style, not mandatory""".stripMargin):

            println(process(List(1, 2, 3, 4, 5)))
            println(process(Option(20)))
