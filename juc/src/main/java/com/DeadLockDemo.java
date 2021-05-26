package com;

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable{

    private String LockA;
    private String LockB;

    public HoldLockThread(String lockA, String lockB) {
        LockA = lockA;
        LockB = lockB;
    }

    @Override
    public void run() {

        synchronized (LockA) {

            System.out.println(Thread.currentThread().getName()+"\t 自己持有:"+LockA+"\t尝试获得:"+LockB);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (LockB) {
                System.out.println(Thread.currentThread().getName()+"\t 自己持有:"+LockB+"\t尝试获得:"+LockA);
            }
        }
    }
}

public class DeadLockDemo {

    public static void main(String[] args) {

        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA,lockB),"AA").start();
        new Thread(new HoldLockThread(lockB,lockA),"Bb").start();
    }
}
