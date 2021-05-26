package com;

import java.util.ArrayList;
import java.util.List;

public class OverHeadDemo {

    public static void main(String[] args) {

        int  i = 0;
        List<String> list = new ArrayList<>();
        while (true) {

            list.add(String.valueOf(i++).intern());

        }
    }
}
