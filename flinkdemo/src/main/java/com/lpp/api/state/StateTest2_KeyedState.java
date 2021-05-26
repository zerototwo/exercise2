package com.lpp.api.state;

import com.lpp.api.beans.SensorReading;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.state.*;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class StateTest2_KeyedState {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);

//        DataStreamSource<String> inputStream = env.socketTextStream("192.168.10.11", 7777);
        DataStreamSource<String> inputStream = env.readTextFile("D:\\workinstall\\exercise\\flinkdemo\\src\\main\\resources\\sensor.txt");

        DataStream<SensorReading> dataStream = inputStream.map(value->{
            String[] split = value.split(",");
            return new SensorReading(split[0], new Long(split[1]), new Double(split[2]));
        });


        SingleOutputStreamOperator<Integer> map = dataStream.keyBy("id").map(new MyKeyCountMapper());

        map.print();
        env.execute();

    }

    private static class MyKeyCountMapper extends RichMapFunction<SensorReading,Integer> {

        private ValueState<Integer> keyCountState ;
        private ListState<String> myListState;

        private MapState<String,Double> myMapState;

        private ReducingState<SensorReading> myReducingState;

        @Override
        public void open(Configuration parameters) throws Exception {

            keyCountState = getRuntimeContext().getState(new ValueStateDescriptor<Integer>("key-count", Integer.class,0));
            myListState = getRuntimeContext().getListState(new ListStateDescriptor<String>("my-list",String.class));
            myMapState = getRuntimeContext().getMapState(new MapStateDescriptor<String, Double>("my-map", String.class, Double.class));
//            myReducingState = getRuntimeContext().getReducingState(new ReducingStateDescriptor<SensorReading>("my-reduce",SensorReading.class))
        }

        @Override
        public Integer map(SensorReading sensorReading) throws Exception {
            //list state
            for (String str : myListState.get()) {
                System.out.println(str);
            }
            myListState.add("hello");

            //map state
            myMapState.get("1");
            myMapState.put("2",22.2);

            //value
            Integer count = keyCountState.value();
            count++;

            keyCountState.update(count);
            //reduce
//            myReducingState.add(sensorReading);
            return count;
        }

    }
}
