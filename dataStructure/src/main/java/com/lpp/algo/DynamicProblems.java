package com.lpp.algo;

public class DynamicProblems {

    public static void main(String[] args) {

        int[] w = {1, 4, 3};
        int[] val = {1500, 3000, 2000};
        int m = 4;
        //物品个数
        int n = val.length;

        //v[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大的值
        int[][] v = new int[n + 1][m + 1];

        int[][] path = new int[n + 1][m + 1];
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {

                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {

//                    v[i][j] = Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i-1][j];
                    }
                }

            }
        }


        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }

    }
}
