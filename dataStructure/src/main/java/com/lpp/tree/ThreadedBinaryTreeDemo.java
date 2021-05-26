package com.lpp.tree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode1 root = new HeroNode1(1, "tome");
        HeroNode1 node2 = new HeroNode1(3, "tome");
        HeroNode1 node3 = new HeroNode1(6, "tome");
        HeroNode1 node4 = new HeroNode1(8, "tome");
        HeroNode1 node5 = new HeroNode1(10, "tome");
        HeroNode1 node6 = new HeroNode1(14, "tome");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        root.threadedNode(root);
        HeroNode1 left = node5.getLeft();
        HeroNode1 right = node5.getRight();
//        System.out.println(left);
//        System.out.println(right);
       root.threadedList();

    }
}
class HeroNode1 {

    private int no ;
    private String name;
    private HeroNode1 left;
    private HeroNode1 right;
    //0子树 1线索化
    private int leftType;
    private int rightType;
    private HeroNode1 pre;
    public HeroNode1(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public void threadedList() {
        HeroNode1 node  = this;

        while (node != null) {

            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }

            System.out.println(node);
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }

            node = node.getRight();
        }
    }

    public void threadedNode(HeroNode1 node) {

        if (node == null) {
            return;
        }

        threadedNode(node.getLeft());
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }

        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }

        pre = node;
        threadedNode(node.getRight());

    }
    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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

    public void midOrder() {
        if (this.left != null) {
            this.left.midOrder();
        }

        System.out.println(this);

        if (this.right != null) {
            this.right.midOrder();
        }
    }

    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }

        System.out.println(this);
    }

    public HeroNode1 preSearch(int no) {

        if (this.no == no) {
            return this;
        }

        HeroNode1 heroNode = null;
        if (this.left != null) {
            heroNode = this.left.preSearch(no);
        }
        if (heroNode != null) {
            return heroNode;
        }

        if (this.right != null) {
            heroNode = this.right.preSearch(no);
        }
        return heroNode;

    }

    public HeroNode1 midSearch(int no) {

        HeroNode1 heroNode =null;
        if (this.left != null) {
            heroNode = this.left.midSearch(no);
        }
        if (heroNode != null) {
            return heroNode;
        }

        if (this.no == no) {
            return this;
        }

        if (this.right != null) {
            heroNode = this.right.midSearch(no);
        }

        return heroNode;
    }

    public HeroNode1 postSearch(int no) {
        HeroNode1 heroNode =null;
        if (this.left != null) {

            heroNode = postSearch(no);
        }
        if (heroNode != null) {
            return heroNode;
        }

        if (this.right != null) {
            heroNode = postSearch(no);
        }
        if (heroNode != null) {

            return heroNode;
        }
        if (this.no == no) {
            return heroNode;
        }

        return heroNode;
    }

    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left =null;
            return;
        }

        if (this.right != null && this.right.no == no) {
            this.right =null;
            return;
        }

        if (this.left != null) {
            delNode(no);
        }
        if (this.right != null) {
            delNode(no);
        }
    }
    @Override
    public String toString() {
        return "HeroNode1{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode1 getLeft() {
        return left;
    }

    public void setLeft(HeroNode1 left) {
        this.left = left;
    }

    public HeroNode1 getRight() {
        return right;
    }

    public void setRight(HeroNode1 right) {
        this.right = right;
    }
}
