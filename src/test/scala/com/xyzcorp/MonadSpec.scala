package com.xyzcorp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class MonadSpec extends AnyFunSpec with Matchers {
    trait MyMonad[F[_]] {
        def pure[A](a: A): F[A]

        def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]
    }

    describe("a monad is a typeclass that represents flatMap"):
        it("it available for option"):
            given MyMonad[Option] with
                override def pure[A](a: A): Option[A] = Option(a)

                override def flatMap[A, B](fa: Option[A])(f: A => Option[B]): Option[B] = fa.flatMap(f)

            val monadicOption = summon[MyMonad[Option]]
            monadicOption.pure(30) should be(Some(30))

            val result2 = monadicOption.flatMap(Option(30))(x => Option(x + 30))
            result2 should be(Some(60))

            monadicOption.flatMap(Option(20))(x => Option.empty[Int]) should be(empty)
}
