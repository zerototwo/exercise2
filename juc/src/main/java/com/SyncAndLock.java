package com;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SyncAndLock {

    public static void main(String[] args) {
        Data data = new Data();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.m5();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.m6();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.m7();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();
    }
}

class Data{

    private int number =1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();



    public void m5() throws InterruptedException {
        lock.lock();
        while (number != 1) {

            c1.await();
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()+"\t AA");
        }
        number=2;
        c2.signal();
        lock.unlock();
    }
    public void m6() throws InterruptedException {
        lock.lock();
        while (number != 2) {

            c2.await();
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()+"\t BB");
        }
        number=3;
        c3.signal();
        lock.unlock();
    }

    public void m7() throws InterruptedException {
        lock.lock();
        while (number != 3) {

            c3.await();
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()+"\t CC");
        }
        number=1;
        c1.signal();
        lock.unlock();
    }


}
