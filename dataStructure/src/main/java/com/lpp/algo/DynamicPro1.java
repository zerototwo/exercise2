package com.lpp.algo;

public class DynamicPro1 {
    static int[] vs = {0, 2, 4, 3, 7};
    static int[] ws = {0, 2, 3, 5, 5};

    public static void main(String[] args) {

        System.out.println(ks(4,10));

    }

    public static int ks(int i, int c) {
        int result = 0;
        if (i == 0 || c == 0) {

            return result;
        } else if (ws[i] > c) {
            return ks(i - 1, c);
        } else {

            int temp = ks(i - 1, c);
            int temp2 = ks(i-1, c-ws[i])+vs[i];

            result = Math.max(temp, temp2);
            return result;

        }
    }
}
