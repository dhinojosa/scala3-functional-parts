name := "scala3-functional-parts"

version := "0.1"

scalaVersion := "3.4.0"

scalacOptions ++= Seq("-deprecation", "-feature", "-source:future", "-explain")

projectDependencies ++=
  Seq("org.scalatest" %% "scalatest" % "3.2.15" % Test withJavadoc() withSources(),
      "org.scalatest" %% "scalatest-funsuite" % "3.2.15" % Test withJavadoc() withSources(),
      "org.scalatest" %% "scalatest-funspec" % "3.2.15" % Test withJavadoc() withSources()
  )
