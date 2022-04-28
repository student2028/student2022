package examples.sources;

import examples.sources.SensorReading;
import org.apache.flink.api.common.eventtime.Watermark;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Random;

public class CustomSource {
    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);

        env.addSource(new SourceFunction<SensorReading>() {

            volatile boolean running = true;

            @Override
            public void run(SourceContext<SensorReading> out) throws InterruptedException {

                Random random = new Random();
                HashMap<String, Double> hashMap = new HashMap<>();
                for (int i = 0; i < 10; i++) {
                    hashMap.put("sensor_" + (i + 1), 50.0);
                }

                while (running) {
                    for (int i = 0; i < 10; i++) {
                        hashMap.put("sensor_" + (i + 1), hashMap.get("sensor_" + (i + 1)) + random.nextGaussian());
                        SensorReading sensorReading = new SensorReading();
                        sensorReading.setSensortName("sensor_" + (i + 1));
                        sensorReading.setMetric(hashMap.get("sensor_" + (i + 1)));
                        sensorReading.setTs(LocalDateTime.now());
                        out.collect(sensorReading);
                    }
                    Thread.sleep(1000);
                }
            }

            @Override
            public void cancel() {
                running = false;
            }
        })
                .addSink(new SinkFunction<SensorReading>() {
                    @Override
                    public void invoke(SensorReading value, Context context) throws Exception {
                        System.out.println(value.toString());
                    }

                    @Override
                    public void writeWatermark(Watermark watermark) throws Exception {
                        System.out.println(watermark);
                    }

                    @Override
                    public void finish() throws Exception {
                        System.out.println("finish");
                    }
                });


        env.execute();


    }
}
