package com.lpp.search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {

    public static void main(String[] args) {

    }

    public static int binarySearch(int[] arr, int left, int right, int findVal) {

        if (left > right) {
            return -1;
        }
        int mid = (left + right)/2;

        if (findVal > arr[mid]) {
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < arr[mid]) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }



    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {

        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right)/2;

        if (findVal > arr[mid]) {
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < arr[mid]) {
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {


            int temp = mid;
            List<Integer> list = new ArrayList<>();
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                list.add(temp);

            }
            temp = mid +1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                list.add(temp);
            }

            return list;
        }
    }
}
