package com.lpp.rabbitmq.ps;

import com.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

public class PsRecvX {

    private final static String QUEUE_NAME = "test_queue_work1";

    private final static String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws Exception {

        //获取连接和通道
        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //将队列绑定到交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

        //同一时刻服务器只会发送一条消息给消费者
        channel.basicQos(1);

        //定义消费者队列
        QueueingConsumer consumer = new QueueingConsumer(channel);

        //监听队列，手动返回完成
        channel.basicConsume(QUEUE_NAME, false, consumer);

        while (true) {

            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [X] Received '" + message + "'");
            Thread.sleep(10);

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);

        }


    }
}
