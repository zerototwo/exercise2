package com.lpp.rabbitmq.topic;

import com.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class TopicSend {

    public static final String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();


        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        String message = "Hello World";

        channel.basicPublish(EXCHANGE_NAME, "routekey.kk", null, message.getBytes());

        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
