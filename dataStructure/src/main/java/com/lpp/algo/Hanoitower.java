package com.lpp.algo;

public class Hanoitower {

    public static void main(String[] args) {

    }


    public static void hanoiTower(int num, char a, char b, char c) {

        if (num == 1) {
            System.out.println("第1个盘从" + a + "->" + c);
        } else {

            //A->B
            hanoiTower(num - 1, a, c, b);
            //A->C
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            //B->C
            hanoiTower(num-1,b,a,c);
        }


    }
}
