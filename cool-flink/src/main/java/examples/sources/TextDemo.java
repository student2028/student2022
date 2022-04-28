package examples.sources;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.typeinfo.TypeInfo;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.io.RowCsvInputFormat;
import org.apache.flink.connector.file.src.FileSource;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.connector.file.src.reader.TextLineFormat;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.io.File;
import java.time.Duration;

public class TextDemo {

    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        final Path filePath = Path.fromLocalFile(new File("/Users/student2020/code/student/student2022/cool-flink/src/main/resources/data/"));
        //read from text file
        final FileSource<String> source =
                FileSource.forRecordStreamFormat(new TextLineFormat(), filePath)
                        .monitorContinuously(Duration.ofSeconds(1L))
                        .build();
        final DataStream<String> stream =  env.fromSource(source, WatermarkStrategy.noWatermarks(), "file-source");

        stream.print();

       env.execute("text demo");
    }
}
