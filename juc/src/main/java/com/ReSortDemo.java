package com;

public class ReSortDemo {

    int a = 0;
    boolean flag = false;


    public void m1() {
        a = 1;
        flag = true;
    }

    public void m2() {


        if (flag) {

            a = a+5;
            System.out.println("a+" + 1);
        }

    }
}
