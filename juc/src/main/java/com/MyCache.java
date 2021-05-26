package com;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class MyCache {

    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock lock =new ReentrantReadWriteLock();
    public void put(String key, Object value) {
        lock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + "\t 正在写入:" + key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "\t 写入完成:" + key);
        lock.writeLock().unlock();
    }

    public void get(String key) {
        lock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + "\t 正在读入:" + key);
        map.get(key);
        System.out.println(Thread.currentThread().getName() + "\t 读入完成:" + key);
        lock.readLock().unlock();
    }

    public void clearMap() {
        map.clear();
    }
    public static void main(String[] args) {

        MyCache myCache = new MyCache();
        for (int i = 0; i < 10; i++) {

            final int temp =i;
            new Thread(()->{

                myCache.put(temp+"",temp+"");
            },String.valueOf(i)).start();

        }

        for (int i = 0; i < 10; i++) {

            final int temp =i;
            new Thread(()->{

                myCache.get(temp+"");
            },String.valueOf(i)).start();

        }
    }
}
