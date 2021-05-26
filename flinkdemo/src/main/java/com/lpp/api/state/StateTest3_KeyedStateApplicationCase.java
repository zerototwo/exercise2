package com.lpp.api.state;

import com.lpp.api.beans.SensorReading;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class StateTest3_KeyedStateApplicationCase {

    public static void main(String[] args) throws Exception {


        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);

        DataStreamSource<String> inputStream = env.socketTextStream("192.168.10.11", 7777);

        DataStream<SensorReading> dataStream = inputStream.map(value->{
            String[] split = value.split(",");
            return new SensorReading(split[0], new Long(split[1]), new Double(split[2]));
        });


        SingleOutputStreamOperator<Tuple3<String, Double, Double>> id = dataStream.keyBy("id").flatMap(new TempChangeWarning(10.0));

        id.print();
        env.execute();

    }

    private static class TempChangeWarning  extends RichFlatMapFunction<SensorReading, Tuple3<String,Double,Double>> {

        private Double threshold;

        private ValueState<Double> lastTempState;

        public TempChangeWarning(Double threshold) {
            this.threshold = threshold;
        }

        @Override
        public void open(Configuration parameters) throws Exception {
            lastTempState =   getRuntimeContext().getState(new ValueStateDescriptor<Double>("value-state",Double.class));
        }


        @Override
        public void close() throws Exception {
            lastTempState.clear();
        }

        @Override
        public void flatMap(SensorReading sensorReading, Collector<Tuple3<String, Double, Double>> collector) throws Exception {

            Double lastTemp = lastTempState.value();
            if (lastTemp != null) {

                Double diff = Math.abs(sensorReading.getTemperature() - lastTemp);
                if (diff >= threshold) {
                    collector.collect(new Tuple3<>(sensorReading.getId(), lastTemp, sensorReading.getTemperature()));
                }
            }

            lastTempState.update(sensorReading.getTemperature());
        }
    }
}
