package com;

public class StackOverFlowErrorDemo {

    public static void main(String[] args) {
        overflow();
    }

    private static void overflow() {

        overflow();
    }
}
