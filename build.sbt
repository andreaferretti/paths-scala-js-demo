enablePlugins(ScalaJSPlugin)

name := "paths-scala-js-demo"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.7"

persistLauncher in Compile := true

skip in packageJSDependencies := false

scalacOptions ++= Seq(
  "-feature",
  "-deprecation"
)

libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.8.0",
    "com.github.japgolly.scalajs-react" %%% "core" % "0.8.1",
    "eu.unicredit" %%% "paths-scala-js" % "0.4.5"
)

jsDependencies ++= Seq(
  "org.webjars" % "react" % "0.12.1" / "react-with-addons.js" commonJSName "React"
)