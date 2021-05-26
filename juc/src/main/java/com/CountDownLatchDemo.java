package com;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {

            new Thread(()->{

                System.out.println(Thread.currentThread().getName()+"\t 上完自习，离开教室");
                countDownLatch.countDown();
            },String.valueOf(1)).start();
        }

        countDownLatch.await();

        System.out.println(Thread.currentThread().getName()+"\t 班长走了关门");
    }
}
