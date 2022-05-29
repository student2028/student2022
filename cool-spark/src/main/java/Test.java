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

        spark.sql("select 1 as orderid, 100 as amount ,1 geoid , current_timestamp() as ts ")
                .createOrReplaceTempView("order");

        spark.sql("select 1 as geoid, 'china' as name , current_timestamp() as ts ")
                .createOrReplaceTempView("geo");


        spark.sql("select * from order join geo on order.geoid = geo.geoid").show();



    }

}
