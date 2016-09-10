name := "fractal-core"

version := "0.0.1"

scalaVersion := "2.11.8"

scalaSource in Compile := baseDirectory.value / "src"

scalacOptions in ThisBuild ++= Seq("-feature", "-deprecation")
