package demo

import java.io.{File, PrintWriter}
import java.nio.file.{Files, Paths}

object iodemo extends App {

  val path = "/tmp/data/csv/students.csv"

  case class Student(sid: Int, sname: String, age: Int) {
    override def toString(): String = {
      s"$sid,$sname,$age"
    }
  }
  println(scala.io.Source.fromFile(new File(path)).mkString)

  def write2file() = {
    val list = (1 to 10).toList.map(i => {
      Student(i, s"student$i", scala.util.Random.nextInt(10) + 10)
    })
    list.foreach(item => println(item))
    if (!Files.exists(Paths.get("/tmp/data/csv")))
      new File("/tmp/data/csv").mkdirs()
    val file = new File(path)
    val writer = new PrintWriter(file)
    list.foreach(stu => writer.println(stu))
    writer.close()
  }

}
