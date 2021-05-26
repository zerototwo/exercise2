package com.lpp.stack;

public class ArrayStackDemo {

    public static void main(String[] args) {

    }
}

class ArrayStack {

    private int maxSize;
    private int[] stack;
    private int top =-1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public boolean isFull() {
        return top == maxSize -1;

    }

    public boolean isEmpty() {

        return top == -1;
    }

    public void push(int value) {

        if (isFull()) {
            System.out.println("栈满");
            return;
        }

        stack[++top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("栈空");
            throw new RuntimeException("栈空");
        }
        int value = stack[top--];

        return value;
    }
}
