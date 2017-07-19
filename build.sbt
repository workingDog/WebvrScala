
enablePlugins(ScalaJSPlugin)

name := "webvrscala"

organization := "com.github.workingDog"

version := (version in ThisBuild).value

scalaJSStage in Global := FullOptStage // FastOptStage

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.2",
  "io.scalajs" %%% "dom-html" % "0.4.0"
)

jsDependencies += RuntimeDOM

skip in packageJSDependencies := false

homepage := Some(url("https://github.com/workingDog/WebvrScala"))

licenses := Seq("Apache 2" -> url("http://www.apache.org/licenses/LICENSE-2.0"))

scalacOptions ++= Seq(
  "-deprecation", // Emit warning and location for usages of deprecated APIs.
  "-feature", // Emit warning and location for usages of features that should be imported explicitly.
  "-unchecked", // Enable additional warnings where generated code depends on assumptions.
  "-Xlint" // Enable recommended additional warnings.
)
