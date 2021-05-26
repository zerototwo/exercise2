package com.lpp.api.processFunction;

import com.lpp.api.beans.SensorReading;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

public class ProcessTest2_KeyedProcessFunction {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);

        DataStreamSource<String> inputStream = env.socketTextStream("192.168.10.11", 7777);

        DataStream<SensorReading> dataStream = inputStream.map(value->{
            String[] split = value.split(",");
            return new SensorReading(split[0], new Long(split[1]), new Double(split[2]));
        });

        dataStream.keyBy("id")
                .process(new TemConIncreWarning(10))
                .print();

        env.execute();

    }

    private static class TemConIncreWarning extends KeyedProcessFunction<Tuple,SensorReading,String> {

        private Integer interval;

        public TemConIncreWarning(Integer interval) {
            this.interval = interval;
        }

        private ValueState<Double> lastTempState;
        private ValueState<Long> timeTsState;

        @Override
        public void open(Configuration parameters) throws Exception {
            lastTempState = getRuntimeContext().getState(new ValueStateDescriptor<Double>("last-temp", Double.class, Double.MIN_VALUE));
            timeTsState = getIterationRuntimeContext().getState(new ValueStateDescriptor<Long>("time-ts",Long.class));

        }

        @Override
        public void processElement(SensorReading sensorReading, Context context, Collector<String> collector) throws Exception {
                //

            Double lastTemp = lastTempState.value();
            Long timeTs = timeTsState.value();
            if (sensorReading.getTimestamp() > lastTemp && timeTs == null) {

                Long ts = context.timerService().currentProcessingTime() + interval * 1000L;
                context.timerService().registerProcessingTimeTimer(ts);
                timeTsState.update(ts);

            } else if (sensorReading.getTemperature() < lastTemp && timeTs != null) {
                context.timerService().deleteProcessingTimeTimer(timeTs);
                timeTsState.clear();
            }

            lastTempState.update(sensorReading.getTemperature());

        }

        @Override
        public void onTimer(long timestamp, OnTimerContext ctx, Collector<String> out) throws Exception {

            System.out.println("warning.......");
            timeTsState.clear();
        }
    }
}
