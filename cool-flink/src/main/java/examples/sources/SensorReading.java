package examples.sources;


import java.time.LocalDateTime;

/**
 * 传感器生成的数据
 * 传感器编码 传感温度 时间
 */
public class SensorReading {

    public String sensortName;
    public Double metric;
    public LocalDateTime ts;

    public SensorReading() {}

    public SensorReading(String sensortName, Double metric, LocalDateTime ts) {
        this.sensortName = sensortName;
        this.metric = metric;
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "SensorReading{" +
                "sensortName='" + sensortName + '\'' +
                ", metric=" + metric +
                ", ts=" + ts +
                '}';
    }

    public String getSensortName() {
        return sensortName;
    }

    public void setSensortName(String sensortName) {
        this.sensortName = sensortName;
    }

    public Double getMetric() {
        return metric;
    }

    public void setMetric(Double metric) {
        this.metric = metric;
    }

    public LocalDateTime getTs() {
        return ts;
    }

    public void setTs(LocalDateTime ts) {
        this.ts = ts;
    }
}
