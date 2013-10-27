import sbt._
import Keys._
import org.scalatra.sbt._
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._

object ReadermarkScalatraBuild extends Build {
  val Organization = "com.github.zgagnon"
  val Name = "ReaderMark Scalatra"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.10.2"
  val ScalatraVersion = "2.2.1"
  val FirebaseVersion = "1.0.7"

  lazy val project = Project(
    "readermark-scalatra",
    file("."),
    settings = Defaults.defaultSettings ++ ScalatraPlugin.scalatraWithJRebel ++ scalateSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers += Classpaths.typesafeReleases,
      libraryDependencies ++= Seq(
        "com.firebase" % "firebase-client" % "1.0.7",
        "org.scalatra" %% "scalatra" % ScalatraVersion,
        "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
        "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
        "org.scalacheck" %% "scalacheck" % "1.10.1" % "test",
        "org.specs2" %% "specs2" % "2.2.3" % "test",
        "ch.qos.logback" % "logback-classic" % "1.0.6" % "runtime",
        "org.eclipse.jetty" % "jetty-webapp" % "8.1.8.v20121106" % "container",
        "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;provided;test" artifacts (Artifact("javax.servlet", "jar", "jar"))
      ),
      scalateTemplateConfig in Compile <<= (sourceDirectory in Compile) {
        base =>
          Seq(
            TemplateConfig(
              base / "webapp" / "WEB-INF" / "templates",
              Seq.empty, /* default imports should be added here */
              Seq(
                Binding("context", "_root_.org.scalatra.scalate.ScalatraRenderContext", importMembers = true, isImplicit = true)
              ), /* add extra bindings here */
              Some("templates")
            )
          )
      }
    )
  )
}
