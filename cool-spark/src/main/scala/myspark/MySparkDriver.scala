package myspark

import org.apache.spark.network.util.JavaUtils
import org.apache.spark.sql.SparkSession

import java.io.File

object MySparkDriver extends  App {

  val spark = SparkSession.builder()
    .appName("MySparkSessionExtensionSuiteUsingWithExtensions")
    .master("local[2]")
    .config("spark.sql.legacy.createHiveTableByDefault","false")
    //.config("spark.sql.extensions", "myspark.MySparkSessionExtension")
    .withExtensions(new MySparkSessionExtension)
    .getOrCreate()

  val file = new File("spark-warehouse")
  if(file.exists()) JavaUtils.deleteRecursively(file)

  spark.sql("create table test(id int ,name string)")
  spark.sql("insert into test values(1,'1') ")
  spark.sql("insert into test values(2,'2') ")
  spark.sql("insert into test values(3,'3') ")
  spark.sql("insert into test values(4,'4') ")
  spark.sql("insert into test values(7,'7') ")
  spark.sql("insert into test values(8,'8') ")
  spark.sql("insert into test values(9,'9') ")

  //show data files for table
  spark.sql("COMPACT  TABLE TEST INTO 2 FILES")

}
