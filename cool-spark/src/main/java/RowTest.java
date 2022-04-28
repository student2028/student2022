import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.List;

public class RowTest {
    public static void main(String[] args) {



        SparkSession spark = SparkSession.builder().master("local").getOrCreate();
        spark.sparkContext().setLogLevel("error");

        //read from cos and then resubmit to salesforce to test
        String path = "/Users/student2020/data/json/sf.json";
        Dataset<Row> df = spark.read().format("json").load(path);
        df.show(20);
         List<Row> rowList = df.sort("servicesAgreement","lineItemSequenceNumber").collectAsList();
       //  df.sort("servicesAgreement","lineItemSequenceNumber").show(20);
        rowList.sort((r1,r2) -> -(r1.getString(r1.schema().fieldIndex("servicesAgreement")) + r1.get(r1.schema().fieldIndex("lineItemSequenceNumber")))
                .compareTo (r2.getString(r2.schema().fieldIndex("servicesAgreement")) + r2.get(r2.schema().fieldIndex("lineItemSequenceNumber")))
        );

        for(Row row : rowList) System.out.println(row);

        //servicesAgreement string lineItemSequenceNumber long 根据这两个进行排序 升序排列 如果放在List里面该怎么操作？


    }
}
