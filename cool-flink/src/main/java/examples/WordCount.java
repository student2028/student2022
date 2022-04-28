package examples;

import org.apache.commons.io.FileUtils;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;

/**
 * https://nightlies.apache.org/flink/flink-docs-release-1.13/zh/docs/dev/datastream/java_lambdas/
 * java8 lambda and flink api
 */
public class WordCount {

    public static void main(String[] args) throws Exception {

        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        final String path = "file:///Users/student2020/code/student/student2022/cool-flink/src/main/resources/test.txt";

        final String outPath = "/tmp/flink/output";

        DataSet<String> strs = env.readTextFile(path);
        DataSet<Tuple2<String, Integer>> counts = strs.flatMap((String x, Collector<Tuple2<String, Integer>> o) -> Arrays.asList(x.split("\\s+")).stream()
                .forEach(w -> o.collect(new Tuple2<String, Integer>(w, 1))))
                .returns(Types.TUPLE(Types.STRING, Types.INT))
                .groupBy(0)
                .sum(1);
        counts.print();
        FileUtils.deleteDirectory(new File(outPath));
        counts.writeAsText(outPath).setParallelism(1);
        env.execute("wordcount");

    }
}
