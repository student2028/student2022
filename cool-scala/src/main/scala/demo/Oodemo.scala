package demo

object Oodemo extends  App {

  class Animal
  class Dog(val name : String) extends Animal {
       def printName = println(s"my name is $name")
  }

  val aDog = new Dog("dog1")
  aDog.printName
  trait  walking {
    def walk
  }

  class Cat(name : String) extends  Animal with walking{
    override def walk: Unit = {
      println(s"I am a cat named $name, I can walk long time")
    }
  }

  val aCat = new Cat("cat1")
  aCat.walk

  case class Person(name : String, age:Int)
  case class Student(name:String, age:Int, className:String)

  val aPerson = Person("p1",20)
  println(aPerson)
  val aStudent = Student("student1",18, "class1")
  println(aStudent)

  // all pairs between the numbers 1, 2, 3 and the letters 'a', 'b', 'c'
  val allPairs = List(1,2,3).flatMap(number => List('a', 'b', 'c').map(letter => s"$number-$letter"))

  // for comprehensions
  val alternativePairs = for {
    number <- List(1,2,3)
    letter <- List('a', 'b', 'c')
  } yield s"$number-$letter"

}
