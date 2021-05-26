package com.lpp;

import java.util.Objects;

public class Demo {


    public static boolean find(int[][] array, int target) {

        if (Objects.isNull(array)) {
            return false;
        }

        int row = 0;
        int colum = array[0].length-1;

        while (row < array.length && colum>=0) {

            if (array[row][colum] == target) {
                return true;
            }

            if (array[row][colum] > target) {
                colum--;
            } else {
                row++;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] array ={{1,2,3},{4,5,6},{7,8,9},{10}};

        System.out.println(find2(array,10));
    }

    public static  boolean find2(int[][] array,int target) { if (array == null) { return false; }int row = 0; int column = array[0].length-1; while (row < array.length && column >= 0) { if(array[row][column] == target) { return true; }if(array[row][column] > target) { column--; } else { row++; } }return false; }
}
