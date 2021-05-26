package com.lpp.avl;

public class AVLTreeDemo {

    public static void main(String[] args) {
        int[] arr = {4, 3, 6, 5, 7, 8};

        AVLTree tree = new AVLTree();

        for (int i = 0; i < arr.length; i++) {
            tree.add(new Node(arr[i]));
        }

        System.out.println(tree.getRoot().height());
        System.out.println("左子树高度:"+tree.getRoot().leftHight());
        System.out.println("右子树高度:"+tree.getRoot().rithtHight());


        int[] arr2 = {10, 12, 8, 9, 7, 6};

        AVLTree tree2 = new AVLTree();

        for (int i = 0; i < arr2.length; i++) {
            tree2.add(new Node(arr2[i]));
        }

        System.out.println(tree2.getRoot().height());
        System.out.println("左子树高度:"+tree2.getRoot().leftHight());
        System.out.println("右子树高度:"+tree2.getRoot().rithtHight());

        int[] arr3 = {10, 11, 7, 6, 8, 9};

    }
}


class AVLTree{
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);

        }
    }

    public void delNode(int value) {
        Node targetNode = search(value);

        if (targetNode == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }

        Node parent = searchParent(value);

        if (targetNode.left == null && targetNode.right == null) {

            if (parent.left != null && parent.left.value == value) {
                parent.left = null;
            } else if (parent.right != null && parent.right.value == value) {
                parent.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) {

            int minVal = delRightTreeMin(targetNode.right);
            targetNode.value = minVal;
        }else {
            if (parent != null) {

                if (parent.left.value == value) {

                    if (targetNode.left != null) {
                        parent.left = targetNode.left;
                    } else {
                        parent.left = targetNode.right;
                    }

                } else {
                    if (targetNode.left != null) {
                        parent.right = targetNode.left;
                    } else {
                        parent.right = targetNode.right;
                    }

                }

            } else {

                if (targetNode.left != null) {
                    root = targetNode.left;
                } else {
                    root = targetNode.right;
                }
            }

        }

    }

    public int delRightTreeMin(Node node) {
        Node target = node;

        while (target.left != null) {
            target = target.left;
        }

        delNode(target.value);

        return target.value;
    }

    public Node search(int value) {

        return root.search(value);
    }

    public Node searchParent(int value) {
        return root.searchParent(value);
    }


    public void midOrder() {
        root.midOrder();
    }
}

class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    public int leftHight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    public int rithtHight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    public void leftRotate() {
        //
        Node newNode = new Node(value);
        //把新的节点的左子树设置成当前节点的左子树
        newNode.left = left;
        //把新节点的右子树设置成当前节点的右子树的左子树
        newNode.right = right.left;
        //把当前节点的值替换成右子节点的值
        value = right.value;
        //把当前节点的右子树 设置成当前接点的右子树的右子树
        right = right.right;
        //把当前节点的左子树设置成新的节点
        left = newNode;
    }

    public void rightRotate() {
        Node newNode = new Node(value);

        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right  = newNode;
    }

    public int height() {
        return Math.max(left==null?0:left.height(),right==null?0:right.height())+1;
    }

    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        }else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {

            if (this.value>value&&this.left!=null) {
                return this.left.searchParent(value);
            } else if (this.value <= value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }

        }
    }


    public void add(Node node) {

        if (node == null) {
            return;
        }

        if (node.value < this.value) {

            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }

        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }

        if (rithtHight() - leftHight() > 1) {

            if (right != null && right.leftHight() > right.rithtHight()) {
                right.rightRotate();
                leftRotate();
            } else {

            leftRotate();
            }

            return;
        }

        if (leftHight() - rithtHight() > 1) {

            if (left != null && left.rithtHight() > left.leftHight()) {
                left.leftRotate();
                rightRotate();
            } else {

            rightRotate();
            }

            return;
        }
    }

    public void midOrder() {

        if (this.left != null) {
            this.left.midOrder();
        }
        System.out.println(this.value);
        if (this.right != null) {
            this.right.midOrder();
        }
    }
}

