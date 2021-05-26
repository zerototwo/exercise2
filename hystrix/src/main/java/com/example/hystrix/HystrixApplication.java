package com.example.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HystrixApplication {

    public static void main(String[] args) {
        SpringApplicationRunListener
        SpringApplication.run(HystrixApplication.class, args);
    }



}
