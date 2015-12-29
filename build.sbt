name := "cricmania"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "org.mongodb" % "mongo-java-driver" % "2.11.3",
  "net.vz.mongodb.jackson" %% "play-mongo-jackson-mapper" % "1.1.0" 
)     

play.Project.playJavaSettings
