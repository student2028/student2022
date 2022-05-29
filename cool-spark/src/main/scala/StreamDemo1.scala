import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.Trigger

import java.io.PrintStream
import java.net.ServerSocket
import java.sql.Timestamp
import scala.concurrent.duration._

object StreamDemo1 {

  val spark = SparkSession.builder().master("local[2]")
    .getOrCreate()

  def getSocket() = {
    //assume data is num,color :num can convert to datetime
    import spark.implicits._
    val df = spark.readStream.format("socket")
      .option("host", "localhost")
      .option("port", 12345)
      .load()
      .as[String]
      .map {
        line =>
          val data = line.split(",")
          val ts = new Timestamp(data(0).toLong)
          val color = data(1)
          (ts, color)
      }.toDF("ts", "color")

    val query = df.writeStream.format("console")
      .outputMode("append")
      .option("truncate","false")
      .trigger(Trigger.ProcessingTime(2.seconds))
      .start()

    query.awaitTermination()

  }

  def main(args: Array[String]): Unit = {
     print("hello scala")
     getSocket()
   }

}

object DataSender extends App {
  val serverSocker = new ServerSocket(12345)
  val socket = serverSocker.accept()
  val printer = new PrintStream(socket.getOutputStream)
  print("accept client")
  while(true) {
    printer.println(s"${System.currentTimeMillis()},red")
    printer.println(s"${System.currentTimeMillis()},red")
    printer.println(s"${System.currentTimeMillis()},red")
    printer.println(s"${System.currentTimeMillis()},blue")
    printer.println(s"${System.currentTimeMillis()},blue")
    printer.println(s"${System.currentTimeMillis()},blue")
    printer.println(s"${System.currentTimeMillis()},blue")
    Thread.sleep(5000)
  }
}
