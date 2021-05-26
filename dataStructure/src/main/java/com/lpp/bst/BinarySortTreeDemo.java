package com.lpp.bst;

public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int arr[] = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree sortTree = new BinarySortTree();

        for (int i = 0; i < arr.length; i++) {
            sortTree.add(new Node(arr[i]));
        }
        sortTree.delNode(2);
        sortTree.delNode(5);
        sortTree.delNode(9);
        sortTree.delNode(12);
        sortTree.delNode(7);
        sortTree.delNode(3);
        sortTree.delNode(10);
//        sortTree.delNode(1);

        sortTree.midOrder();
    }
}


class BinarySortTree {
    private Node root;

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
