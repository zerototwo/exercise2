package com.lpp.api.transform;

import com.lpp.api.beans.SensorReading;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class TransformTest3_Reduce {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);
        DataStreamSource<String> inputStream = env.readTextFile("D:\\workinstall\\exercise\\flinkdemo\\src\\main\\resources\\sensor.txt");


        DataStream<SensorReading> dataStream = inputStream.map(value->{
            String[] split = value.split(",");
            return new SensorReading(split[0], new Long(split[1]), new Double(split[2]));
        });
        //分组
        KeyedStream<SensorReading, Tuple> keyStream = dataStream.keyBy("id");

        keyStream.reduce((curStat, newData) -> new SensorReading(curStat.getId(), newData.getTimestamp(), Math.max(curStat.getTemperature(), newData.getTemperature())));

        dataStream.print();
        env.execute();
    }
}
