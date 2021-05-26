package com.lpp.sort;

import java.util.Arrays;

public class InsertSort {

    public static void main(String[] args) {
        int arr[] = {3, 9, -1, 10, -2};

        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int insertIndex = i-1;

            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex+1] = arr[insertIndex];
//                arr[insertIndex] = insertVal;
                insertIndex -- ;
            }
            if (insertIndex + 1 != i) {

            arr[insertIndex+1] = insertVal;
            }
        }

        System.out.println(Arrays.toString(arr));
    }
}
