package com.lpp.linkedlist;

public class JosepFu {

    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.add(25);
//        list.show();
        list.countBoy(1,2,25);
    }
}

class CircleSingleLinkedList {

    private Boy first = new Boy(-1);

    public void add(int num) {
            Boy curBoy = null;
        for (int i = 1; i <=num ; i++) {

            Boy boy = new Boy(i);

            if (i == 1) {
                first = boy;
                boy.setNext(first);
                curBoy =boy;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy =boy;
            }
        }
    }

    public void show() {

        Boy curBoy = first;
        while (true) {

            System.out.println(curBoy);
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy =curBoy.getNext();

        }
    }

    /**
     *
     * @param startNo 几号开始
     * @param countNum 数记下
     * @param nums 共有多少人
     */
    public void countBoy(int startNo, int countNum, int nums) {

        Boy helper = first;

        while (true) {

            if (first == helper.getNext()) {
                break;
            }
            helper= helper.getNext();
        }

        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        while (true) {

            if (helper == first) {
                break;
            }
            for (int i = 0; i < countNum - 1; i++) {
                first=first.getNext();
                helper = helper.getNext();
            }
            first = first.getNext();
            helper.setNext(first);

        }

        System.out.println(first.getNo());

    }
}


class Boy {

    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
