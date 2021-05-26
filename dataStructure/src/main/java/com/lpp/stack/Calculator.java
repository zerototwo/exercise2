package com.lpp.stack;

public class Calculator {

    public static void main(String[] args) {
        String expr = "70+2*6-2";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        int num1 = 0;
        int num2 = 0;
        int index = 0;
        char ch ;
        char oper;
        int res =0;
        while (true) {

            ch = expr.substring(index,index+1).charAt(0);
            if (operStack.isOper(ch)) {

                if (operStack.isEmpty()) {
                    operStack.push(ch);
                } else {

                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = (char) operStack.pop();
                        operStack.push(ch);
                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);
                    } else {
                        operStack.push(ch);
                    }

                }


            } else {
                numStack.push(ch - 48);
            }
            index++;
            if (index >= expr.length()) {
                break;
            }
        }

        while (true) {

            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper= (char) operStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);

        }

        System.out.println(numStack.pop());
    }
}


class ArrayStack2 {

    private int maxSize;
    private int[] stack;
    private int top =-1;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public int peek() {
        return stack[top];
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

    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    public boolean isOper(char val) {
        return val =='+' || val=='-' || val =='*'||val=='/';
    }

    public int cal(int num1, int num2, int oper) {

        int res = 0;
        switch (oper) {
            case '+':
                res = num1+ num2;
                break;
            case '-':
                res = num2- num1;
                break;
            case '*':
                res = num1*num2;
                break;
            case '/':
                res = num2/num1;
                break;

             default:
                 break;
        }
        return res;
    }
}