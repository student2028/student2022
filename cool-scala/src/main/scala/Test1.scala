
object Test1 extends App {

  println("start job...")

  try {
    println("do it ing ...")
    throw new RuntimeException("test for it ")

  } catch {
    case ex: Exception =>
      ex.printStackTrace()
      sys.exit(-2)
  } finally {
    println("finally do it !!!!")
  }
}
