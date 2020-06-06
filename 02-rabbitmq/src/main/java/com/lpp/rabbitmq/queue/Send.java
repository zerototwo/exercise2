package com.lpp.rabbitmq.queue;

import com.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {

    private final static String QUEUE_NAME = "q_test_01";

    public static void main(String[] args)throws Exception {

        //获取连接
        Connection connection = ConnectionUtil.getConnection();

        //从连接中创建管道
        Channel channel = connection.createChannel();

        //管道中创建队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //消息内容
        String message = "Hello World";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        //关闭管道和连接
        channel.close();
        connection.close();

    }
}
