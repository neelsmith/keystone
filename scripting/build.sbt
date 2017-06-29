

scalaVersion := "2.11.8"
resolvers += Resolver.jcenterRepo
resolvers += Resolver.bintrayRepo("neelsmith","maven")
libraryDependencies ++= Seq(
  "edu.holycross.shot.cite" %% "xcite" % "2.5.0",
  "edu.holycross.shot" %% "ohco2" % "6.11.0",
  "edu.holycross.shot" %% "orca" % "3.0.0",
  "edu.holycross.shot" %% "greek" % "1.3.3",
  "edu.holycross.shot" %% "gsphone" % "1.0.1",
  "org.homermultitext" %% "hmt-textmodel" % "1.2.1"
)
