package com.lpp.hufcode;

import java.util.*;

public class HuffmanCode {

    public static void main(String[] args) {
    String str = "i like like like java do you like a java";
        System.out.println(str.length());
        byte[] bytes = str.getBytes();
        List<Node> nodes = getNodes(bytes);
        Node huf = createHuf(nodes);
//        huf.preOrder();
        hufCode(huf,"",buffer);
        System.out.println(hufCode);

        byte[] zip = zip(bytes, hufCode);

        byte[] decode = decode(hufCode, zip);
        System.out.println(Arrays.toString(decode));
    }




    public static Map<Byte, String> hufCode = new HashMap<>();
    public static StringBuilder buffer = new StringBuilder();


    public static String byteToBiString(boolean flag,byte b) {
        int temp = b;
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);

        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    public static byte[] decode(Map<Byte, String> huffmanCode, byte[] huffmanBytes) {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {

            boolean flag = (i == huffmanBytes.length - 1);

            buffer.append(byteToBiString(!flag,huffmanBytes[i]));
        }

        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCode.entrySet()) {

            map.put(entry.getValue(), entry.getKey());
        }

        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < buffer.length();) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag) {
                String key = buffer.substring(i, i + count);
                b = map.get(key);

                if (b == null) {
                    count++;

                } else {
                    flag = false;
                }


            }
                list.add(b);
            i += count;
        }

        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);

        }
        return b;
    }
    public static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        Node huf = createHuf(nodes);
        hufCode(huf,"",buffer);

        byte[] zip = zip(bytes, hufCode);

        return zip;
    }
    /**
     * 生产hufman编码
     * @param node
     * @param code
     * @param buffer
     */
    public static void  hufCode(Node node,String code,StringBuilder buffer) {
        StringBuilder builder = new StringBuilder(buffer);

        if (node != null) {

            if (node.data == null) {

                hufCode(node.left, "0", builder.append(code));
                hufCode(node.right, "1", builder.append(code));

            } else {

                hufCode.put(node.data, buffer.toString());
            }


        }
    }

    public static byte[] zip(byte[] bytes, Map<Byte, String> hufmanCode) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(hufmanCode.get(b));
        }
        int len;
        if (builder.length() % 8 == 0) {
            len = builder.length() / 8;
        } else {
            len = builder.length() / 8 +1;
        }

        byte[] by = new byte[len];
        int index = 0;
        for (int i = 0; i < builder.length(); i += 8) {
            String strByte;
            if (i + 8 > builder.length()) {
                strByte = builder.substring(i);
            } else {

                strByte = builder.substring(i, i + 8);
            }

            by[index++] = (byte) Integer.parseInt(strByte, 2);

        }

        return by;
    }

    public static List<Node> getNodes(byte[] bytes) {

        List<Node> nodes = new ArrayList<>();

        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b,count+1);
            }
        }

        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {

            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }

        return nodes;
    }

    public static Node createHuf(List<Node> list) {


        Collections.sort(list);

        while (list.size() > 1) {

            Node left = list.get(0);
            Node right = list.get(1);

            Node parent = new Node(null,left.weight+right.weight);
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
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }


    @Override
    public int compareTo(Node o) {

        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
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
}
