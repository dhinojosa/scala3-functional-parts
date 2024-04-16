package com.xyzcorp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class EngineerSpec extends AnyFunSpec with Matchers {
    describe("an engineer") {
        it("has a firstName and a lastName") {
            val martin = new Engineer("Martin", "Odersky")
            martin.firstName should be("Martin")
            martin.lastName should be("Odersky")
        }
        it("has a string representation") {
            val martin = Engineer("Martin", "Odersky")
            val expectedStringRepresentation = "Engineer(Martin,Odersky)"
            martin.toString should be(expectedStringRepresentation)
        }
        it("has a string representation for Guido van Rossum") {
            val guido = Engineer("Guido", "van Rossum")
            val expectedStringRepresentation = "Engineer(Guido,van Rossum)"
            guido.toString should be(expectedStringRepresentation)
        }
        it("should be equal given two engineers with the same first name and the same last name") {
            val guido1 = Engineer("Guido", "van Rossum")
            val guido2 = Engineer("Guido", "van Rossum")
            guido1 should be(guido2)
        }
        it("should have the same hashcode given two engineers with the same first name and the same last name") {
            val guido1 = Engineer("Guido", "van Rossum")
            val guido2 = Engineer("Guido", "van Rossum")
            guido1.hashCode() should be(guido2.hashCode())
        }
        it("should have the same hashcode given two engineers with different first name and the same last name") {
            val james = Engineer("James", "Gosling")
            val guido = Engineer("Guido", "van Rossum")
            james.hashCode() shouldNot be(guido.hashCode())
        }
        it("can have a companion object that would maintain a count") {
            val james = Engineer("James", "Gosling")
            val guido = Engineer("Guido", "van Rossum")
            val bjarne = Engineer("Bjarne", "Strousoup")
            Engineer.count shouldBe >=(3)
        }
    }
}
