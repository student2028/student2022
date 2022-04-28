package examples.sources;

import com.fasterxml.jackson.core.type.TypeReference;
import examples.utils.JsonUtils;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.io.TextInputFormat;
import org.apache.flink.core.fs.Path;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;


//读取parquet文件  直接当textfile读取 然后使用gson/jackson 等工具进行转换即可
//直接使用自带的jsonformat
//可以看到flink现在正在发力 准备把这一块做得更好用
//flink 在流处理方面的应用 尤其是对接kafka这一块 在批这一块 先不用过多关注
//spark在批这一块比flink好用太多 对程序员友好透明

public class JsonDemo {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        //read from csv file
        final String csvFile = "/Users/student2020/code/student/student2022/cool-flink/src/main/resources/json/";

        DataStreamSource<String> rawInput = env.readFile(
                new TextInputFormat(new Path(csvFile)), csvFile);

        DataStream<Student> ds = rawInput.flatMap(new MyJSONTransformer());
        ds.print();

        env.execute("jsonDemo");
    }

    public final static class MyJSONTransformer implements FlatMapFunction<String, Student> {

        @Override
        public void flatMap(String value, Collector<Student> out) throws Exception {
            //convert string to pojo
            Student stu = JsonUtils.serializer().fromJson(value, Student.class);
            out.collect(stu);
        }
    }

 public static class Student {
        private String name;
        private String sex;
        private int age;

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", sex='" + sex + '\'' +
                    ", age=" + age +
                    '}';
        }

        public Student() {
        }

        public Student(String name, String sex, int age) {
            this.name = name;
            this.sex = sex;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }


}
