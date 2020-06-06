package com.lpp.rabbitmq.queue;

import com.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;


public class Recv {

    private final static String QUEUE_NAME = "q_test_01";

    public static void main(String[] args) throws Exception{
        //获取连接
        Connection connection = ConnectionUtil.getConnection();

        //从连接中创建管道
        Channel channel = connection.createChannel();

        //管道中创建队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        
        //监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);
        
        //获取消息
        while (true)
        {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            byte[] body = delivery.getBody();
            String message = new String(body);
            System.out.println(" [x] Received '" + message + "'");
        }

    }
}
