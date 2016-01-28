name := "fs"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "edu.uci.ics" % "crawler4j" % "4.2",
  "com.typesafe" % "config" % "1.3.0",
  "nu.validator.htmlparser" % "htmlparser" % "1.4",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.1",
  "org.scala-lang.modules" %% "scala-xml" %  "1.0.1",
  "org.scala-lang.plugins" %% "scala-continuations-library" % "1.0.1",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
  "ch.qos.logback" % "logback-classic" % "1.1.3"
)