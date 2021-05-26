package com;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

class MyResource{

    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void myProd() throws InterruptedException {
        String result=null;
        while (FLAG) {

            result =atomicInteger.incrementAndGet()+"";
            boolean offer = blockingQueue.offer(result);

            if (offer) {
                System.out.println("生产成功:"+result);
            }else {
                System.out.println("生产失败"+result);
            }
            TimeUnit.SECONDS.sleep(1);
        }

    }

    public void myConsumer() throws InterruptedException {


        while (FLAG) {

            String poll = blockingQueue.poll(2, TimeUnit.SECONDS);

            if (poll == null) {
                System.out.println("未收到消息");
                FLAG =false;
                return;
            }
            System.out.println("消费消息:"+poll);
        }
    }

}

public class ProdConsumer_BlockQueue {

    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));

        new Thread(()->{
            try {
                myResource.myProd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(()->{
            try {
                myResource.myConsumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
