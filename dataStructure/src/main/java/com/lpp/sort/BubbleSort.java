package com.lpp.sort;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {

        int arr[] = {3, 9, -1, 10, -2};

        for (int i = 0; i < arr.length - 1; i++) {
            int temp = 0;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(arr));
    }
}
