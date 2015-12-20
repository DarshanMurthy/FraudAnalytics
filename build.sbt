name := """FraudAnalytics"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,

    "com.amazonaws" % "amazon-kinesis-client" % "1.4.0",
    "org.apache.spark" %% "spark-core" % "1.4.0",
    "org.apache.spark" %% "spark-streaming" % "1.4.0",
    "org.apache.spark" %% "spark-streaming-kinesis-asl" % "1.4.0",
    "com.amazonaws" % "aws-java-sdk" % "1.10.2",
     "org.apache.spark"  % "spark-mllib_2.10"   % "1.1.0"
)

dependencyOverrides ++= Set(
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.4.4"
)



resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
