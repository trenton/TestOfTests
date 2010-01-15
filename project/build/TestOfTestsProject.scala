import sbt._

class TestOfTestsProject(info: ProjectInfo) extends DefaultProject(info)
{
  val junit = "junit" % "junit" % "4.4" % "test"
  val specs = "org.specs" % "specs" % "1.2.5" % "test"
}
