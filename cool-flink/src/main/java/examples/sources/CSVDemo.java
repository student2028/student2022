package examples.sources;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.io.RowCsvInputFormat;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.FileProcessingMode;

public class CSVDemo {

    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        //read from csv file
        final String csvFile = "/Users/student2020/code/student/student2022/cool-flink/src/main/resources/csv/";

        TypeInformation[] csvType = new TypeInformation[3];
        csvType[0] = TypeInformation.of(String.class);
        csvType[1] = TypeInformation.of(String.class);
        csvType[2] = TypeInformation.of(Integer.class);

        RowCsvInputFormat inputFormat = new RowCsvInputFormat(
                null, // input path is configured later
                csvType,
                "\n",
                ",");
        inputFormat.setSkipFirstLineAsHeader(true);
        //FileProcessingMode.PROCESS_CONTINUOUSLY
        env.readFile(inputFormat,csvFile, FileProcessingMode.PROCESS_CONTINUOUSLY,1000)
                .print();

        env.execute("csvDemo");
    }
}
