import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * read data from local file  source/csv file  to target/csv file
 * using spark structure streaming
 * 1. use checkpoint to save meta data what's the content in meta data file?
 *
 * 2. in microbatch mode, how to get the new files or new changes in file?
 *
 * 3. in microbatch mode, how to store the state for stateful task? you know state between batch has miss?
 *
 * FileStreamSource
 * MicroBatchExecution
 * InMemoryFileIndex
 *
 */

import org.apache.spark.sql.functions;
import org.apache.spark.sql.streaming.StreamingQueryException;

import java.util.concurrent.TimeoutException;

public class StreamTest {

    public static void main(String[] args) throws TimeoutException, StreamingQueryException {

        SparkSession spark = SparkSession.builder()
                .config("spark.sql.streaming.schemaInference","true")
                .master("local").getOrCreate();

        final String srcPath = "/tmp/data/spark/src/csv/";
        final String targetPath = "/tmp/data/spark/target/csv/";
        final String checkpointPath = "/tmp/data/spark/checkpoint/demo1";

        /*************************************************
         * sid,sname,age,sex
         * 1,student1,20,M
         * 2,student2,17,M
         * 3,student3,16,M
         **************************************************/
        Dataset<Row> df = spark.readStream().option("header","true")
                .format("csv")
                .load(srcPath)
                .withColumn("ts", functions.current_timestamp());

        df = df.withColumn("catelog", functions.expr("case when age > 17 then 'A' else 'U' end "));

        df.writeStream().option("checkpointLocation",checkpointPath)
                .outputMode("append")
                .format("console")
                .start();

        spark.streams().awaitAnyTermination();



    }
}
