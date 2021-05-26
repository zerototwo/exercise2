package com.lpp.api.transform;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class TransformTest6_Partition {

    public static void main(String[] args) throws Exception {


        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(4);


        DataStreamSource<String> inputStream = env.readTextFile("D:\\workinstall\\exercise\\flinkdemo\\src\\main\\resources\\sensor.txt");


        inputStream.print("inpupt");

        DataStream<String> shuffle = inputStream.shuffle();

        shuffle.print("shuffle");

        inputStream.global().print("global");
        env.execute();

    }
}
