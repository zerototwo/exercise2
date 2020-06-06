package com.lpp.rabbitmq.route;

import com.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

public class RouteRecvY {

    public static final String EXCHANGE_NAME = "test_exchange_direct";
    public static final String QUEUE_NAME = "test_queue_direct_2";

    public static void main(String[] args) throws Exception {
        //1.获取连接和通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //2.申明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //3.绑定队列到交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "insert");
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "update");
        //channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "delete");

        //4.同一时刻服务器只会发送一条消息给消费者
        channel.basicQos(1);

        //5.定义队列消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);

        channel.basicConsume(QUEUE_NAME, false, consumer);

        while (true) {

            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("Y Recv:" + message);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }

    }

}
