package com.lpp.api.state;

import com.lpp.api.beans.SensorReading;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.checkpoint.ListCheckpointed;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Collections;
import java.util.List;

public class StateTest1_OperatorState {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);

        DataStreamSource<String> inputStream = env.socketTextStream("192.168.10.11", 7777);

        DataStream<SensorReading> dataStream = inputStream.map(value->{
            String[] split = value.split(",");
            return new SensorReading(split[0], new Long(split[1]), new Double(split[2]));
        });


        SingleOutputStreamOperator<Integer> map = dataStream.map(new MyCountMap());

        map.print();
        env.execute();


    }

    private static class MyCountMap implements MapFunction<SensorReading,Integer>, ListCheckpointed<Integer> {

        private  Integer count;
        @Override
        public Integer map(SensorReading sensorReading) throws Exception {
            count++;
            return count;
        }

        @Override
        public List<Integer> snapshotState(long l, long l1) throws Exception {
            return Collections.singletonList(count);
        }

        @Override
        public void restoreState(List<Integer> list) throws Exception {
            for (Integer num : list) {
                count +=num;
            }
        }
    }
}
