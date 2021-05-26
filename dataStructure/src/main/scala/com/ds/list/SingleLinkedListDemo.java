package com.ds.list;

public class SingleLinkedListDemo {


    public static void main(String[] args) {

        HeroNode her1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode her2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode her3 = new HeroNode(3, "吴用", "智多星");
        HeroNode her4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList list = new SingleLinkedList();

//        list.add(her1);
//        list.add(her2);
//        list.add(her3);

//        list.add(her4);
//
//
        list.addByOrder(her2);
        list.addByOrder(her1);
        list.addByOrder(her3);
        list.addByOrder(her4);
//        list.addByOrder(her4);
//        list.list();

        HeroNode heroNode = new HeroNode(1, "lpp", "lpp");

        list.update(heroNode);
        list.del(6);
        list.list();


    }

    /**
     * //1.获取单链表的节点个数
     * @param head
     * @return
     */
    public static int getLength(HeroNode head) {

        if (head.next == null) {
            return 0;
        }
        int length = 0;

        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }

        return length;
    }


    public HeroNode getLastIndex(HeroNode head, int index) {

        HeroNode temp = head.next;

        if (temp == null) {
            return null;
        }

        int size = getLength(head);
        if (index < 0 || index > size) {
            return null;
        }
        HeroNode cur = temp;
        for (int i = 0; i < size-index; i++) {

            cur = cur.next;

        }

        return cur;


    }
}


class SingleLinkedList{

    private HeroNode head = new HeroNode(0,"","");


    public void del(int no) {

        HeroNode temp = head;
        boolean flag = false;

        while (true) {

            if (temp.next == null) {
                break;
            }

            if (temp.next.no == no) {
                flag = true;
                break;
            }

            temp = temp.next;
        }

        if (flag) {
            temp.next = temp.next.next;
        }else{
            System.out.println("不存在此节点");
        }
    }

    public void add(HeroNode heroNode) {

        HeroNode temp = head;

        while (true) {

            if (temp.next == null) {
                break;
            }

            temp = temp.next;
        }

        temp.next = heroNode;
    }

    public void update(HeroNode heroNode) {

        HeroNode temp = head.next;

        boolean flag =true;

        while (true) {

            if (temp == null) {
                break;
            } else if (temp.no == heroNode.no) {
                flag =true;
                break;
            }

            temp = temp.next;

        }

        if (flag) {
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        } else {
            System.out.println("不存在此节点");
        }

    }

    public void list(){

        if (head.next == null){
            System.out.println("链表为空");
        }

        HeroNode tmep = head.next;


        while (true) {

            if (tmep == null) {
                break;
            }
            System.out.println(tmep);
            tmep = tmep.next;
        }
    }

    public void addByOrder(HeroNode heroNode){

        HeroNode temp = head;

        boolean flag = false;

        while (true) {
            //无节点
            if (temp.next == null) {

                break;
            }

            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }

            temp = temp.next;

        }

        if (flag) {
            System.out.println("节点已存在");
        }else {

        heroNode.next = temp.next;
        temp.next = heroNode;

        }





    }
}

class HeroNode{

    public int no;
    public String name;
    public String nickname;
    public HeroNode next;


    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
