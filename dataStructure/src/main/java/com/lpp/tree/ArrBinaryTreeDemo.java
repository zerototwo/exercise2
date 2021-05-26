package com.lpp.tree;

public class ArrBinaryTreeDemo {

    public static void main(String[] args) {

    }
}

class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }


    public void preOrder(int index) {

        System.out.println(arr[index]);
        if ((2 * index + 1) < arr.length) {
            preOrder(2*index+1);
        }

        if ((2 * index + 2) < arr.length) {
            preOrder(2*index+2);
        }
    }

}
