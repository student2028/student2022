
import org.apache.spark.sql.{DataFrame, Encoders, SparkSession}

import java.sql.Timestamp
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.Trigger

import java.io.PrintStream
import java.net.ServerSocket
import scala.concurrent.duration._

object deduplicate {
  val spark = SparkSession.builder().master("local[2]").getOrCreate()
  spark.sparkContext.setLogLevel("error")

  //get data from socket assume data is : id, time, metric
  def getDataFromSocket() = {
    import spark.implicits._
    val df = spark.readStream.format("socket")
      .option("host", "localhost")
      .option("port", 12346)
      .load()
      .as[String](Encoders.STRING)
      .map {
        line =>
          val data = line.split(",")
          val id = data(0).toLong
          val ts = new Timestamp(data(1).toLong)
          val metric = data(2).toDouble
          (id, ts, metric)
      }.toDF("id", "ts", "metric")
    df
  }

  def deduplicate(df: DataFrame) = {
      df
      .dropDuplicates("id")
      .writeStream
      .format("console")
      .option("truncate", "false")
      .outputMode("update")
      .trigger(Trigger.ProcessingTime(5.seconds))
      .start()
      .awaitTermination()

  }

  def justoutput(df: DataFrame) = {
    df.writeStream
      .format("console")
      .option("truncate", "false")
      .outputMode("append")
      .trigger(Trigger.ProcessingTime(5.seconds))
      .start()
      .awaitTermination()
  }


  def main(args: Array[String]): Unit = {
    val df = getDataFromSocket()
    deduplicate(df)
    justoutput(df)

  }

}


object DataSender2 extends App {
  val serverSocker = new ServerSocket(12346)
  val socket = serverSocker.accept()
  val printer = new PrintStream(socket.getOutputStream)
  print("accept client")
  while (true) {
    printer.println(s"1,${System.currentTimeMillis()},1")
    printer.println(s"2,${System.currentTimeMillis()},2")
    printer.println(s"3,${System.currentTimeMillis()},3")
    printer.println(s"1,${System.currentTimeMillis()},11")
    printer.println(s"2,${System.currentTimeMillis()},12")
    printer.println(s"3,${System.currentTimeMillis()},13")
    printer.println(s"3,${System.currentTimeMillis()},14")
    Thread.sleep(5000)
  }
}



