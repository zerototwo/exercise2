package com;

public class CreateThreadDemo {

    public static void main(String[] args) {


        for (int i = 0; ; i++) {
            System.out.println(i);
            new Thread(()->{

                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
