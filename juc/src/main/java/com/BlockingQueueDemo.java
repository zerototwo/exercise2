package com;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.put("c");
        blockingQueue.put("c");
        blockingQueue.put("c");
        blockingQueue.put("c");
        blockingQueue.put("c");

    }
}
