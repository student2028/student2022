package demo

import scala.annotation.tailrec

object practice1 {

   def main(args:Array[String]) = {
     println("hello tail recursion")

     val list = insert(2, 1 :: 3 :: Nil)
     println(list)

     list.map(x => x+ 1).foreach(println)
     val xs = List(1, 2, 3).flatMap { x =>
         List(x, 2 * x, 3 * x)
       }
     println(xs == List(1, 2, 3, 2, 4, 6, 3, 6, 9))

     println("hello")

   }

  val cond: (Int, Int) => Boolean = _ < _

  def insert(x: Int, xs: List[Int]): List[Int] =
    xs match {
      case List() => x :: Nil
      case y :: ys =>
        if (cond(x, y)) x :: y :: ys
        else y :: insert(x, ys)
    }

  @tailrec
  def tailRecursiveLength(list: List[String], accumulator: Long): Long = list match {
    case Nil => accumulator
    case head :: tail => tailRecursiveLength(tail, accumulator + 1)
  }

}
