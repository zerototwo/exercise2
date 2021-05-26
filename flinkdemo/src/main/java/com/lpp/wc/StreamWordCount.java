package com.lpp.wc;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class StreamWordCount {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

//        env.setParallelism(4);
//        String inputPath = "D:\\workinstall\\exercise\\flinkdemo\\src\\main\\resources\\hello.txt";
//
//        DataStreamSource<String> inputDataStream = env.readTextFile(inputPath);
////
//        ParameterTool parameterTool = ParameterTool.fromArgs(args);
//        String host = parameterTool.get("host");
//        Integer port = parameterTool.getInt("port");

        DataStreamSource<String> inputDataStream = env.socketTextStream("192.168.10.11",7777);

        SingleOutputStreamOperator<Tuple2<String, Integer>> sum =
                inputDataStream
                        .flatMap(new WordCount.MyFlatMapper())
                        //流出里keyby，不同于批处理，因为批处理数据已存在，流处理是实时计算
                        .keyBy(0)
                        .sum(1).setParallelism(2);

        sum.print().setParallelism(1);
        env.execute();

    }
}
