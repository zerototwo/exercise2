package com.lpp.rabbitmq.topic;

import com.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

public class TopicRecvY {

    private final static String QUEUE_NAME = "test_queue_topic_work_2";

    private final static String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] args) throws Exception {

        //1.获取连接和通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //2.声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //3.绑定队列和交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "*.*");

        //4.定义服务器统一时刻发送消息数
        channel.basicQos(1);

        //5.定义消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);

        //6.监听队列
        channel.basicConsume(QUEUE_NAME, false, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [Y] Received '" + message + "'");
            Thread.sleep(10);

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);

        }
    }
}
