package com;

import java.util.Random;

public class JavaHeapSpaceDemo {

    public static void main(String[] args) {

        String str = "lpp";

        while (true) {

            str+=str+new Random().nextInt(111111111)+new Random().nextInt(222222222);
            str.intern();
        }
    }
}
