package com;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceDemo {


    public static void main(String[] args) {

        Object o = new Object();

        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();

        PhantomReference<Object> reference = new PhantomReference<>(o, referenceQueue);


        System.out.println(o);
        System.out.println(reference.get());
        System.out.println(referenceQueue.poll());
        System.out.println("**********************************");

        o = null;
        System.gc();

        System.out.println(o);
        System.out.println(reference.get());
        System.out.println(referenceQueue.poll());
    }

}
