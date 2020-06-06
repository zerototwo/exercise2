package com;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {

    public static Connection getConnection() throws Exception{
        //1.定义连接工程
        ConnectionFactory factory = new ConnectionFactory();
        //2.设置服务地址
        factory.setHost("localhost");
        //3.端口
        factory.setPort(5672);
        //4.设置虚拟信息
        factory.setVirtualHost("admin");
        factory.setUsername("admin");
        factory.setPassword("123456");
        Connection connection = factory.newConnection();
        return connection;
    }
}
