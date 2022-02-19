import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.storage.StorageLevel;
import pojo.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 测试 数据写入到cache 使用不同的序列化框架数据的存储情况
 * kryo 不支的类 :没有空参构造函数 非static 内部类 异常类 lambda表达式  ArrayBlockingQueue不支持
 * kyro 依赖底层使用asm操作字节码
 */
public class RDDTest {


    public static void main(String[] args) throws InterruptedException {


        SparkConf conf = new SparkConf();
        conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
        conf.registerKryoClasses(new Class[]{Student.class});
        SparkSession spark = SparkSession.builder().master("local")
                .config(conf)
                .getOrCreate();
        //java list to RDD
        List<Student> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            list.add(new Student(i, "name" + i, "china-beijing", random.nextInt(60) + 20));
        }


        JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());
        JavaRDD<Student> rdd = jsc.parallelize(list);
        //rdd.cache();//898.5 KiB

        // rdd.persist(StorageLevel.MEMORY_ONLY_SER());//300.3 KiB

        //403.6 KiB	不注册Student类的时候大小
        rdd.persist(StorageLevel.MEMORY_ONLY_SER());//276.7 KiB 注册kyro类之后大小 明显变小了


        System.out.println(rdd.first());

        Thread.sleep(10000000);

    }
}
