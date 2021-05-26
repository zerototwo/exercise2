package com.lpp.api.transform;

import com.lpp.api.beans.SensorReading;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class TransformTest2_RollingAggregation {

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

//        KeyedStream<SensorReading, String> keyStream = dataStream.keyBy(data -> data.getId());

        SingleOutputStreamOperator<SensorReading> resultStream = keyStream.maxBy("temperature");

        resultStream.print();

        env.execute();
    }
}
