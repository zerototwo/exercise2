package com.lpp.api.sink;

import com.lpp.api.beans.SensorReading;
import org.apache.flink.api.common.functions.RichFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SinkTest4_Jdbc {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(4);


        DataStreamSource<String> inputStream = env.readTextFile("D:\\workinstall\\exercise\\flinkdemo\\src\\main\\resources\\sensor.txt");

        // 转换成SensorReading类型
        DataStream<SensorReading> dataStream = inputStream.map(line -> {
            String[] fields = line.split(",");
            return new SensorReading(fields[0], new Long(fields[1]), new Double(fields[2]));
        });


        dataStream.addSink(new MyJdbcSink());

//        dataStream.print();

        env.execute();
    }

    private static class MyJdbcSink extends RichSinkFunction<SensorReading> {

        Connection connection = null;

        PreparedStatement insert = null;
        PreparedStatement update = null;



        @Override
        public void open(Configuration parameters) throws Exception {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lpp?useSSL=false&serverTimezone=Asia/Shanghai","root","123456");

            insert = connection.prepareStatement("insert into sensor_temp(id,temp) values(?,?) ");
            update = connection.prepareStatement("update  sensor_temp set temp = ? where id = ?");



        }
        @Override
        public void invoke(SensorReading value, Context context) throws Exception {

            update.setDouble(1,value.getTemperature());
            update.setString(2,value.getId());
            update.execute();

            if (update.getUpdateCount() == 0) {
                insert.setString(1, value.getId());
                insert.setDouble(2, value.getTemperature());
                insert.execute();
            }

        }

        @Override
        public void close() throws Exception {
            update.close();
            insert.close();
            connection.close();
        }
    }
}
