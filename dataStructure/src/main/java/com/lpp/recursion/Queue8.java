package com.lpp.recursion;

public class Queue8 {

    int max =8;
    int[] array = new int[max];
    static int count = 0;
    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println(count);
    }


    public void check(int n) {
        if (n == max) {
            print();
            return;
        }

        for (int i = 0; i < max; i++) {
            array[n] =i;
            if (judge(n)) {
                check(n + 1);
            }
        }
    }
    public void print() {
        for (int i = 0; i < max; i++) {
            System.out.print(array[i]+" ");
        }
        count++;
        System.out.println();
    }

    public boolean judge(int n) {

        for (int i = 0; i < n; i++) {

            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[i] - array[n])) {
                return false;
            }
        }


        return true;
    }

}
