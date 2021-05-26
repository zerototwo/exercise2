package com.lpp.sort;

import java.util.Arrays;

public class SelectSort {

    public static void main(String[] args) {

        int arr[] = {3, 9, -1, 10, -2};
        int length =arr.length - 1;
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex=i;
            int min = arr[i];
            for (int j = i+1; j < arr.length ; j++) {

                if (min >arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }


        System.out.println(Arrays.toString(arr));
    }
}
