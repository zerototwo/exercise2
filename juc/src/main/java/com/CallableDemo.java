package com;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> task = new FutureTask<>(() -> {

            System.out.println("####################");
            return 1024;
        });

        Thread t1 = new Thread(task);

        t1.start();

        System.out.println("*******:"+task.get());
    }
}
