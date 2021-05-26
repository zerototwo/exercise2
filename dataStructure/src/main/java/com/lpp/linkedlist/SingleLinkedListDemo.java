package com.lpp.linkedlist;

public class SingleLinkedListDemo {

    public static void main(String[] args) {

    }
}


class SingleLinkedList{

    private HeroNode head = new HeroNode(0, "", "");


    public void addByOrder(HeroNode heroNode) {

        HeroNode temp = head;
        boolean flag = false;

        while (true) {

            if (temp.next == null) {
                break;
            }

            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {

                flag =true;
                break;
            }

            temp = temp.next;

        }

        if (true) {
            System.out.println("元素已存在");

        }else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    public void del(int no) {

        HeroNode temp = head;

        boolean flag = false;

        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag =true;
                break;
            }

        }

        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("不存在此元素");
        }
    }
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;

        while (true) {

            if (temp.next == null) {
                break;
            }

            System.out.println(temp);

            temp = temp.next;
        }
    }

    /**
     * 统计节点个数
     */

    public int getSize() {
        HeroNode temp = head;

        if (temp.next == null) {
            return 0;
        }
        int length = 0;
        while (temp.next != null) {

            length++;
            temp = temp.next;
        }

        return length;
    }


    public void reverse() {

        if (head.next == null || head.next.next == null) {
            return;
        }

        HeroNode cur = head.next;
        HeroNode next = null;
        HeroNode reversNode = new HeroNode(0,"","");

        while (cur.next != null) {

            next = cur.next;
            cur.next = reversNode.next;
            reversNode.next = cur;
            cur= next;
        }

        head.next = reversNode.next;


    }
}


class HeroNode{

    public int no;
    public String name;
    public String ncikname;
    public HeroNode next;

    public HeroNode(int no, String name, String ncikname) {
        this.no = no;
        this.name = name;
        this.ncikname = ncikname;
    }


}
