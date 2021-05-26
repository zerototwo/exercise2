package com;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class WeakHashMapDemo {


    public static void main(String[] args) {

        Object o = new Object();

        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();

        WeakReference<Object> weakReference = new WeakReference<>(o, referenceQueue);

        System.out.println(o);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());


        System.out.println("===========================");
        o = null;
        System.gc();

        System.out.println(o);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

    }


}
