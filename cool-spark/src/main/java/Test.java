import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {

    public static void main(String[] args) throws InterruptedException {


        SparkSession spark = SparkSession.builder().master("local").getOrCreate();

        spark.sql("select current_date() as dt, current_timestamp() as ts ").show();

        //测试一下啊
        Random random = new Random();
        Dataset<Row> df =  spark.range(10000).toDF("id")
                .withColumn("name", functions.expr("'name'||id"))
                .withColumn("age", functions.lit(random.nextInt(100)) )
                .withColumn("addr", functions.lit("china beijing"));
        df.cache();
        df.count();

      //  spark.stop();
        Thread.sleep(10000000);

    }

}
