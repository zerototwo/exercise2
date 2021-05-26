package com.lpp.linkedlist;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {

    }
}

class DoubleLinkedList {

    private HeroNode2 head = new HeroNode2(0, "", "");

    public void add(HeroNode2 heroNode2) {

        HeroNode2  temp = head;

        while (true) {

            if (temp.next == null) {
                break;
            }

            temp = temp.next;

        }

        temp.next = heroNode2;
        heroNode2.pre = temp;
    }
}

class HeroNode2{

    public int no;
    public String name;
    public String ncikname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String ncikname) {
        this.no = no;
        this.name = name;
        this.ncikname = ncikname;
    }




}
