name := "play-statsd"
    
organization := "com.typesafe.play.plugins"

version := "2.5.0"

scalaVersion := "2.11.1"

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % "2.5.18" % "provided",
  "com.typesafe.play"  %% "play-test" % "2.5.18" % "test",
  specs2 % "test"
)

parallelExecution in Test := false

testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v")

publishTo <<= (version) { version: String =>
  val nexus = "https://private-repo.typesafe.com/typesafe/"
  if (version.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "maven-snapshots/") 
  else                                   Some("releases"  at nexus + "maven-releases/")
}
 
javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint:unchecked", "-Xlint:deprecation", "-encoding", "UTF-8")

scalacOptions += "-deprecation"
  
lazy val root = project in file(".")

lazy val sample = (project in file("sample/sample-statsd"))
  .enablePlugins(PlayScala)
  .settings(
    Keys.fork in Test := false,
    scalaVersion := "2.11.7",
    scalacOptions += "-deprecation",
    routesGenerator := StaticRoutesGenerator,
    libraryDependencies ++= Seq(
      ws,
      "com.typesafe.play" %% "play" % "2.5.0" % "provided",
      "com.typesafe.play"  %% "play-test" % "2.5.0" % "test",
      specs2 % "test"
    )
  ).dependsOn(root).aggregate(root)
