package com.lpp.rabbitmq.work;

import com.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;


public class WorkSend {

    private final static String QUEUE_NAME = "test_queue_work";

    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        for (int i = 0; i < 100; i++) {

            String message = ""+i;

            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
            Thread.sleep(i * 10);
        }

        channel.close();
        connection.close();

    }
}
