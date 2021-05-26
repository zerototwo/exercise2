package com;

public class SingletonDemo {

    private static SingletonDemo instance = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 我是构造方法");
    }

    public static SingletonDemo getInstance() {

        if (instance == null) {
            synchronized (SingletonDemo.class) {

                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }

        return instance;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {

            new Thread(()->{

                System.out.println();

            },String.valueOf(i)).start();
        }
    }
}
