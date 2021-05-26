package com;

import java.lang.ref.SoftReference;

public class SoftReferenceDemo {

    public static  void enough() {
        Object o = new Object();
        SoftReference<Object> reference = new SoftReference<>(o);

        System.out.println(o);
        System.out.println(reference.get());


        o =null;
        System.gc();

        System.out.println(o);
        System.out.println(reference.get());

    }

    public static void not_enough() {
        Object o = new Object();
        SoftReference<Object> reference = new SoftReference<>(o);

        System.out.println(o);
        System.out.println(reference.get());


        o =null;
        System.gc();

        try {
            byte[] b = new byte[1000*1024*1024];
        }catch (Throwable throwable){

        }finally {
            System.out.println(o);
            System.out.println(reference.get());
        }

    }

    public static void main(String[] args) {
//        enough();
        not_enough();
    }
}
