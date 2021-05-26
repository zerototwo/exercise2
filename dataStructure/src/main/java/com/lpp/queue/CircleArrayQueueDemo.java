package com.lpp.queue;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {

    }
}

class CircleArrayQueue {

    private int maxSize;
    private int front;
    private int rear;
    private int [] arr;

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }


    public boolean isFull() {

        return (rear+1)%maxSize == front;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public void add(int n) {
        if (isFull()) {
            System.out.println("队列满");
            return;
        }

         arr[rear] = n;
        rear = (rear+1)%maxSize;
    }

    public int get() {

        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        int value = arr[front];
        front = (front+1)%maxSize;

        return value;
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("队列为空");
        }
        for (int i = front;i<front+size();i++){
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i % maxSize]);
        }
    }


    public int size() {
        return (rear+maxSize-front) % maxSize;
    }
}
