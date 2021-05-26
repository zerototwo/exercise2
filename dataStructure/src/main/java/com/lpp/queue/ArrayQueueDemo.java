package com.lpp.queue;

public class ArrayQueueDemo {

    public static void main(String[] args) {

    }
}

class ArrayQueue {

    private int maxSize;
    private int front;
    private int rear;
    private int [] arr;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;
        rear =-1;
    }

    public boolean isFull() {

        return rear == maxSize -1;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public void add(int n){
        if (isFull()) {
            System.out.println("队列满");
            return;
        }
        arr[++rear] = n;
    }

    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }

        return arr[++front];
    }
    public void show() {

        if (isEmpty()) {
            System.out.println("队列为空");
        }

        for (int i = front + 1; i <= rear; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }
}
