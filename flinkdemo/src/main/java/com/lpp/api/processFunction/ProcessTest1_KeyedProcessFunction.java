package com.lpp.api.processFunction;

import com.lpp.api.beans.SensorReading;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

public class ProcessTest1_KeyedProcessFunction {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);

        DataStreamSource<String> inputStream = env.socketTextStream("192.168.10.11", 7777);

        DataStream<SensorReading> dataStream = inputStream.map(value->{
            String[] split = value.split(",");
            return new SensorReading(split[0], new Long(split[1]), new Double(split[2]));
        });

            dataStream.keyBy("id")
                    .process(new MyProcess())
                    .print();

            env.execute();

    }

    private static class MyProcess extends KeyedProcessFunction<Tuple,SensorReading,Integer> {
        @Override
        public void processElement(SensorReading sensorReading, Context context, Collector<Integer> collector) throws Exception {

            collector.collect(sensorReading.getId().length());

            context.timestamp();
            context.getCurrentKey();
            context.timerService().currentWatermark();
            context.timerService().registerProcessingTimeTimer(10000L);
            context.timerService().registerEventTimeTimer((sensorReading.getTimestamp()+10)*1000L);
            context.timerService().deleteProcessingTimeTimer(10000L);
        }

        @Override
        public void onTimer(long timestamp, OnTimerContext ctx, Collector<Integer> out) throws Exception {
            System.out.println(timestamp+"ffff");
        }
    }
}
