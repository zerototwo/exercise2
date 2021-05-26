package com.lpp.sort;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {8,4,5,7,1,3,6,2};
        quickSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }


    public static void quickSort(int[] arr, int left, int right) {

        if (left >= right) {
            return;
        }
        int l = left;
        int r = right;
        int povit = arr[(left+right)/2];
        int temp;
        while (l < r) {

            while (arr[l] < povit&&l<=r) {
                l+=1;
            }
            while (arr[r] > povit) {
                r-=1;
            }

            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

        }



    }
}
