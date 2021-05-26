package com;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone extends Thread{


    public synchronized void sendSMS()throws Exception {

        System.out.println(Thread.currentThread().getId()+"\t sendSMS");
        sendEmail();
    }
    public synchronized void sendEmail()throws Exception {

        System.out.println(Thread.currentThread().getId()+"\t sendEmail");
    }

    @Override
    public void run() {
        get();
    }

    Lock lock = new ReentrantLock();
    public void get() {
        lock.lock();
        System.out.println(Thread.currentThread().getId()+"\t sendSMS");
        set();
        lock.unlock();
    }

    public void set() {
        lock.lock();
        System.out.println(Thread.currentThread().getId()+"\t sendEmail");
        lock.unlock();
    }
}


public class ReentrantLockDemo {

    public static void main(String[] args) throws Exception {
        Phone phone = new Phone();

//        phone.sendSMS();
        phone.start();
    }
}
