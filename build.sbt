name := "fp-ws-playground"

version := "0.1"

scalaVersion := "2.13.2"

val circeVersion = "0.13.0"
val http4sVersion = "0.21.4"
val http4sWsClientVersion = "0.3.0"
val catsEffectVersion = "2.1.3"
val enumeratumVersion = "1.6.0"

val `fp-ws-playground` = project
  .in(file("."))
  .settings(
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core",
      "io.circe" %% "circe-generic",
      "io.circe" %% "circe-generic-extras",
      "io.circe" %% "circe-parser"
    ).map(_ % circeVersion),
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-core",
      "org.http4s" %% "http4s-dsl",
      "org.http4s" %% "http4s-circe",
      "org.http4s" %% "http4s-client"
    ).map(_ % http4sVersion),
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-jdk-http-client" % http4sWsClientVersion
    ),
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-effect" % catsEffectVersion
    ),
    libraryDependencies ++= Seq(
      "com.beachape" %% "enumeratum" % enumeratumVersion,
      "com.beachape" %% "enumeratum-circe" % enumeratumVersion
    ),
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.1" % "test"
  )