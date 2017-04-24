name := """spray-test"""

version := "1.0"

scalaVersion := "2.11.6"

// enablePlugins(JavaServerAppPackaging)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % "test")


mainClass in Global := Some("com.example.quiz.management.Main")

assemblyJarName in assembly := "quiz-management-server.jar"


resolvers ++= Seq("Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Spray Repository" at "http://repo.spray.io")


libraryDependencies ++= {
  val akkaVersion = "2.3.11"
  val sprayVersion = "1.3.2"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "io.spray" %% "spray-can" % sprayVersion,
    "io.spray" %% "spray-routing" % sprayVersion,
    "io.spray" %% "spray-json" % "1.3.1",
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "ch.qos.logback" % "logback-classic" % "1.1.7",
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
    "io.spray" %% "spray-testkit" % sprayVersion % "test" excludeAll(
      ExclusionRule ("org.scalaz"),
      ExclusionRule ("org.specs2"),
      ExclusionRule ("org.scalacheck")
      ),
    "org.specs2" % "specs2_2.10" % "3.3.1" % "test"
  )
}


