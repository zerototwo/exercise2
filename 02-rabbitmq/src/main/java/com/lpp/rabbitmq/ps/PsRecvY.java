package com.lpp.rabbitmq.ps;

import com.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

public class PsRecvY {

    private final static String QUEUE_NAME = "test_queue_work2";

    private final static String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws Exception {

        //1.获取连接和通道
        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        //2.声明队令
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //3.绑定交换寄
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

        channel.basicQos(1);
        //4.创建消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);

        //5.监听队列，手动确认
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
