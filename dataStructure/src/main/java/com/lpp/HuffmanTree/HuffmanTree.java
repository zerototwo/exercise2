package com.lpp.HuffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {

    public static void main(String[] args) {

        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        Node huf = createHuf(arr);
    huf.preOrder();
    }

    public static Node createHuf(int[] arr) {

        List<Node> list = new ArrayList<>();
        for (int value : arr) {
            list.add(new Node(value));
        }
        Collections.sort(list);

        while (list.size() > 1) {

            Node left = list.get(0);
            Node right = list.get(1);

            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;
            list.remove(left);
            list.remove(right);
            list.add(parent);

            Collections.sort(list);

        }

        return list.get(0);
    }
}

class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
