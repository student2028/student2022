package demo

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

object advanced extends  App {

  lazy val lazyValue = 10;
  lazy val lazyValueWithSideEffect = {
    println("I am so very lazy!")
    43
  }

  def methodWhichCanReturnNull(): String = "hello, Scala"
  val anOption = Option(methodWhichCanReturnNull()) // Some("hello, Scala")
  // option = "collection" which contains at most one element: Some(value) or None

  val stringProcessing = anOption match {
    case Some(string) => s"I have obtained a valid string: $string"
    case None => "I obtained nothing"
  }

  def methodWhichCanThrowException(): String = throw new RuntimeException
  val aTry = Try(methodWhichCanThrowException())
  // a try = "collection" with either a value if the code went well, or an exception if the code threw one

  val anotherStringProcessing = aTry match {
    case Success(validValue) => s"I have obtained a valid string: $validValue"
    case Failure(ex) => s"I have obtained an exception: $ex"
  }
  val aFuture = Future {
    println("Loading...")
    Thread.sleep(1000)
    println("I have computed a value.")
    67
  }

   aFuture.onComplete( x => x match  {
    case Success(v) => println(v)
    case Failure(_) => println("failed ")
  })

  Thread.sleep(2000)

}
