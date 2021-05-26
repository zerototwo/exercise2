package com;


import java.util.concurrent.TimeUnit;

class MyData{

    //int number = 0;
   volatile int number = 0;

    public void addTo60(){
        this.number = 60;
    }

    public void addPlus() {

        this.number++;
    }
}

/**
 * 1.验证可见性
 */
public class VolatileDemo {

    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 0; i < 20; i++) {

            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    myData.number++;
                }
            },String.valueOf(i)).start();
        }
//        try {
//            TimeUnit.SECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t number:"+myData.number);
    }

    private static void seeOkVolatile() {
        MyData myData = new MyData();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t come in");

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            myData.addTo60();
            System.out.println(Thread.currentThread().getName()+"\t updated number value:"+myData.number);
        },"AAA").start();


        while (myData.number == 0) {

        }

        System.out.println(Thread.currentThread().getName() + "\t mission is over");
    }
}
