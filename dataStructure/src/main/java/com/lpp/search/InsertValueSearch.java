package com.lpp.search;

public class InsertValueSearch {

    public static void main(String[] args) {

    }

    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {

        if (left > right) {
            return -1;
        }
        //后面需要研究
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);

        if (findVal > arr[mid]) {
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < arr[mid]) {
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
