import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;

import java.util.Random;

public class TestS3 {

    public static void main(String[] args) {

        SparkSession spark = SparkSession.builder().master("local").getOrCreate();

        spark.sql("select current_date() as dt, current_timestamp() as ts ").show();

        //测试一下啊
        Random random = new Random();
        Dataset<Row> df =  spark.range(100).toDF("id")
                .withColumn("name", functions.expr("'name'||id"))
                .withColumn("age", functions.lit(random.nextInt(100)) )
                .withColumn("addr", functions.lit("china beijing"));

        df.show();

        spark.sparkContext().hadoopConfiguration().set("fs.s3a.access.key", "minio");
        spark.sparkContext().hadoopConfiguration().set("fs.s3a.secret.key", "minio123");
        spark.sparkContext().hadoopConfiguration().set("fs.s3a.endpoint","localhost:9000");
        spark.sparkContext().hadoopConfiguration().set("fs.s3a.path.style.access", "true");
        spark.sparkContext().hadoopConfiguration().set("fs.s3a.connection.ssl.enabled", "false");
        spark.sparkContext().hadoopConfiguration().set("fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem");
        spark.sparkContext().hadoopConfiguration().set("spark.hadoop.com.amazonaws.services.s3.enableV2", "true");
        spark.sparkContext().hadoopConfiguration().set("spark.hadoop.fs.s3a.aws.credentials.provider","org.apache.hadoop.fs.s3a.SimpleAWSCredentialsProvider");

        df.write().format("parquet").save("s3a://test/student");
    }
}
