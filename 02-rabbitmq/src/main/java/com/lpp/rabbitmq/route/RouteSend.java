package com.lpp.rabbitmq.route;

import com.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class RouteSend {

    public static final String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws Exception {
        //1.获取连接和通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //2.声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        //3.消息内容
        String message = "删除商品";

        channel.basicPublish(EXCHANGE_NAME, "delete", null, message.getBytes());
        System.out.println("X send "+message);

        channel.close();
        connection.close();

    }
}
