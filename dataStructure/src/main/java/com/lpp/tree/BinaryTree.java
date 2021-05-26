package com.lpp.tree;

public class BinaryTree {

    private HeroNode root;
    public static void main(String[] args) {


        BinaryTree tree = new BinaryTree();

        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "吴俊宇");
        HeroNode node4 = new HeroNode(4, "林冲");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        tree.setRoot(root);
        tree.preOrder();
        System.out.println("========================");
        tree.midOrder();
        System.out.println("========================");
        tree.postOrder();

    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void preOrder() {
        root.preOrder();
    }
    public void midOrder() {
        root.midOrder();
    }
    public void postOrder() {
        root.postOrder();
    }

    public void delNode(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                this.root.delNode(no);
            }
        }
    }

}

class HeroNode {

    private int no ;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
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

    public HeroNode preSearch(int no) {

        if (this.no == no) {
            return this;
        }

        HeroNode heroNode = null;
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

    public HeroNode midSearch(int no) {

        HeroNode heroNode =null;
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

    public HeroNode postSearch(int no) {
        HeroNode heroNode =null;
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
        return "HeroNode{" +
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }
}
