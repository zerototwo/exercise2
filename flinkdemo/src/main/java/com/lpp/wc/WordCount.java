package com.lpp.wc;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

//批处理，针对离线数据
public class WordCount {
    public static void main(String[] args) throws Exception {

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        String inputPath = "D:\\workinstall\\exercise\\flinkdemo\\src\\main\\resources\\hello.txt";

        DataSource<String> dt = env.readTextFile(inputPath);

        DataSet<Tuple2<String,Integer>> resultSet =  dt.flatMap(new MyFlatMapper())
                //按照第一位置word分組，位置
                .groupBy(0)
                .sum(1);//第二个位置

        resultSet.print();
    }


    public static class MyFlatMapper implements FlatMapFunction<String, Tuple2<String,Integer>>{
        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> collector) throws Exception {

            String[] words = value.split(" ");

            for (String word : words) {
                collector.collect(new Tuple2<>(word,1));
            }
        }
    }
}
