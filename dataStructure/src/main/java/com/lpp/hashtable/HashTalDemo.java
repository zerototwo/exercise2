package com.lpp.hashtable;

public class HashTalDemo {

    public static void main(String[] args) {

    }
}

class HashTab{
    private EmpLinkedList [] empLinkedLists;
    private int size;
    public HashTab(int size) {
        this.size = size;
        this.empLinkedLists = new EmpLinkedList[size];
        for (int i = 0; i < empLinkedLists.length; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp) {

    }

    public int hash(int id) {
        return id%size;
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedLists[i].list();
        }
    }
}

class Emp{

    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class EmpLinkedList{

    private Emp head;

    public void add(Emp emp) {

        if (head == null) {
            head = emp;
            return;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }

        curEmp.next = emp;
    }

    public void list() {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }

        Emp curEmp = head;
        while (true) {
            System.out.println(curEmp);

            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
    }
}
