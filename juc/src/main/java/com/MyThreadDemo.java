package com;

import java.util.concurrent.*;

public class MyThreadDemo {


    public static void main(String[] args) {

        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());


        for (int i = 0; i < 20; i++) {

            threadPool.execute(()->{

                System.out.println(Thread.currentThread().getName()+"\t 办理业务");
            });
        }
    }
}
