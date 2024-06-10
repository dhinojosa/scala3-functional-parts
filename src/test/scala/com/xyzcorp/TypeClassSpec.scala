package com.xyzcorp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class TypeClassSpec extends AnyFunSpec with Matchers {

    trait Show[A]:
        def show(a: A): String

    describe(
        """a type class is a behavior modeled with a trait, there are a
          |  few common ones you should know""".stripMargin):
        it("can be modeled to determine to perform a toString logic without baking it into the class"):
            case class Team(city: String, name: String, wins: Int, losses: Int)


            given Show[Team] with
                def show(t: Team): String = s"${t.name}: (${t.wins} - ${t.losses})"

            val giants = Team("San Francisco", "Giants", 12, 3)
            println(giants)
            println(summon[Show[Team]].show(giants))

    describe("a common strategy is to house recipes of type classes in an object"):
        it("can be done with our team"):
            case class Team(city: String, name: String, wins: Int, losses: Int)
            object Team:
                given showTeamAndRecord: Show[Team] = new Show[Team]:
                    def show(t: Team): String = s"${t.name}: (${t.wins} - ${t.losses})"

                given showCityTeamAndRecord: Show[Team] = new Show[Team]:
                    def show(t: Team): String = s"${t.city} ${t.name}: (${t.wins} - ${t.losses})"

                given showCityAndTeam: Show[Team] = new Show[Team]:
                    def show(t: Team): String = s"${t.city} ${t.name}"

            val tigers = Team("Detroit", "Tigers", 19, 2)
            note("The import declares which one we want available in this scope")
            import Team.showCityAndTeam
            val string = summon[Show[Team]].show(tigers)
            string should be("Detroit Tigers")
    describe("A type class is usually required in a method declaration"):
        it("can declared in a separate parameter list"):

            case class Team(city: String, name: String, wins: Int, losses: Int)

            object Team:
                given showTeamAndRecord: Show[Team] = new Show[Team]:
                    def show(t: Team): String = s"${t.name}: (${t.wins} - ${t.losses})"

                given showCityTeamAndRecord: Show[Team] = new Show[Team]:
                    def show(t: Team): String = s"${t.city} ${t.name}: (${t.wins} - ${t.losses})"

                given showCityAndTeam: Show[Team] = new Show[Team]:
                    def show(t: Team): String = s"${t.city} ${t.name}"

            note("here we are using, using, a keyword meant to bring in whichever one show is in scope")

            def showMatchup(home: Team, away: Team)(using show: Show[Team]): String =
                s"The matchup is between ${show.show(home)} and ${show.show(away)}"

            import Team.showCityTeamAndRecord
            val tigers = Team("Detroit", "Tigers", 19, 2)
            val giants = Team("San Francisco", "Giants", 12, 3)
            println(showMatchup(giants, tigers))
    describe("There is another very common typeclass called Eq, which defines how two items are equal to one another"):
        it("can be done with an Eq[T] typeclass which we can begin with a trait"):
            trait Eq[T]:
                def eqv(a: T, b: T): Boolean

            case class Team(city: String, name: String, wins: Int, losses: Int)

            given Eq[Team] = new Eq[Team]:
                override def eqv(a: Team, b: Team): Boolean = a.name == b.name && a.city == b.city

            val tigers1 = Team("Detroit", "Tigers", 19, 2)
            val tigers2 = Team("Detroit", "Tigers", 20, 1)
            val giants = Team("San Francisco", "Giants", 12, 3)

            val result1 = summon[Eq[Team]].eqv(tigers1, tigers2)
            result1 should be(true)

            val result2 = summon[Eq[Team]].eqv(tigers1, giants)
            result2 should be(false)
    describe("Ordering is a typeclass built into the standard library it is responsible for how we order in Scala"):
        it("is defined already with a Ordering[T] typeclass"):
            note("the trait is already defined, so let's just use it")
            case class Team(city: String, name: String, wins: Int, losses: Int)
            given Ordering[Team] with
                override def compare(x: Team, y: Team): Int =
                    val cityComparison = x.city.compare(y.city)
                    if cityComparison != 0 then cityComparison
                    else x.name.compare(y.name)

            val teams: List[Team] = List(
                Team("Los Angeles", "Dodgers", 55, 25),
                Team("San Francisco", "Giants", 52, 28),
                Team("Houston", "Astros", 48, 32),
                Team("Tampa Bay", "Rays", 50, 30),
                Team("Boston", "Red Sox", 49, 31),
                Team("Atlanta", "Braves", 47, 33),
                Team("Philadelphia", "Phillies", 45, 35),
                Team("Miami", "Marlins", 42, 38)
            )

            note("sorted.head and min are the same thing")
            teams.min.city should be("Atlanta")
    it("is defined already with a Ordering[T] typeclass and here we will perform win percentage"):
        case class Team(city: String, name: String, wins: Int, losses: Int) {
            def winPercentage: Double = wins.toDouble / (wins + losses)
        }
        given Ordering[Team] with
            override def compare(x: Team, y: Team): Int =
                x.winPercentage compare y.winPercentage

        val teams: List[Team] = List(
            Team("Los Angeles", "Dodgers", 55, 25),
            Team("San Francisco", "Giants", 52, 28),
            Team("Houston", "Astros", 48, 32),
            Team("Tampa Bay", "Rays", 50, 30),
            Team("Boston", "Red Sox", 49, 31),
            Team("Atlanta", "Braves", 47, 33),
            Team("Philadelphia", "Phillies", 45, 35),
            Team("Miami", "Marlins", 42, 38)
        )

        teams.max.city should be("Los Angeles")

}
