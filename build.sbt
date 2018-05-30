name := "coinbase-scala"

version := "1.0"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "com.softwaremill.sttp" %% "core" % "1.1.14",
  "com.softwaremill.sttp" %% "akka-http-backend" % "1.1.14",
  "com.typesafe.akka" %% "akka-stream" % "2.5.11",
  "com.lihaoyi" %% "upickle" % "0.6.6",
  "com.lihaoyi" %% "ujson" % "0.6.6",
  "org.scalatest" %% "scalatest" % "3.0.5" % Test


)
        