package com.xyzcorp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class EngineerSpec extends AnyFunSpec with Matchers {
  describe("an engineer") {
      it ("has a first name and a last name") {
          val martin = Engineer("Martin", "Odersky")
          martin.firstName should be ("Martin")
          martin.lastName should be ("Odersky")
      }
  }
}
