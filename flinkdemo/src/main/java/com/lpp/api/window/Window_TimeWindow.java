package com.lpp.api.window;

import com.lpp.api.beans.SensorReading;
import org.apache.commons.collections.IteratorUtils;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.apache.flink.util.IterableIterator;
import org.apache.flink.util.IterableUtils;

public class Window_TimeWindow {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);
//        DataStreamSource<String> inputStream = env.readTextFile("D:\\workinstall\\exercise\\flinkdemo\\src\\main\\resources\\sensor.txt");
        DataStreamSource<String> inputStream = env.socketTextStream("192.168.10.11", 7777);

        DataStream<SensorReading> dataStream = inputStream.map(value->{
            String[] split = value.split(",");
            return new SensorReading(split[0], new Long(split[1]), new Double(split[2]));
        });
        //1.增量聚合函數
        SingleOutputStreamOperator<Integer> res = dataStream.keyBy("id")
                .timeWindow(Time.seconds(15))
//                .window(TumblingEventTimeWindows.of(Time.seconds(15)))
                .aggregate(new AggregateFunction<SensorReading, Integer, Integer>() {
                    @Override
                    public Integer createAccumulator() {
                        return 0;
                    }

                    @Override
                    public Integer add(SensorReading sensorReading, Integer integer) {
                        return integer + 1;
                    }

                    @Override
                    public Integer getResult(Integer integer) {
                        return integer;
                    }

                    @Override
                    public Integer merge(Integer integer, Integer acc1) {
                        return integer + acc1;
                    }
                });

        //全窗口函數
        SingleOutputStreamOperator<Integer> result = dataStream.keyBy("id")
                .timeWindow(Time.seconds(15))
                .apply(new WindowFunction<SensorReading, Integer, Tuple, TimeWindow>() {
                    @Override
                    public void apply(Tuple tuple, TimeWindow timeWindow, Iterable<SensorReading> iterable, Collector<Integer> collector) throws Exception {

                        Integer count = IteratorUtils.toList(iterable.iterator()).size();
                        collector.collect(count);
                    }
                });

//        res.print();
        result.print();
        env.execute();
    }
}
