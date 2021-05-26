package com.nacos.demonacos;

import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Value("${key}")
    String value;
    @RequestMapping(value = "/demo")
    public Object test(){

        return  value;
    }
}
