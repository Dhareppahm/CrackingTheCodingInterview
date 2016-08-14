package datastructures.treesgraphs.randomnode;

import java.util.Random;

/**
 Problem 4.11 Random Node
 You are implementing a binary search tree class from scratch, which in addition to INSERT, FIND has a method getRandomNode()
 which returns a random node from tree.

 IMP: All nodes should be  equally likely to be chosen.

 Design and implement an algorithm for getRandomNode() and explain how you would implement the rest of the method.
 */

class Node{
    int data;
    Node left;
    Node right;
    int size;       //store size of subtree from node

    public Node(int data){
        this.data = data;
        this.size = 1;
    }

    /**
     * Insert Node
     */
    public void insert(int val){
        if(val < this.data){    //insert in left subtree
            if(left == null){
                left = new Node(val);
            }else{
                left.insert(val);
            }
        }else{                  //insert into right subtree
            if(right == null){
                right = new Node(val);
            }else{
                right.insert(val);
            }
        }
        this.size++;            //increment size
    }

    /**
     * Find a node with given value
     */
    public Node find(int val){
        if(this.data == val){
            return this;
        }else if(val <= this.data){
            return left == null ? null : this.left.find(val);   //check left subtree
        }else if(val > this.data){
            return right == null ? null : this.right.find(val); //check right subtree
        }
        return null;        //not found
    }

    /**
     * Get Random Node ((Move this logic to i-th node method to reduce random.nextInt call))
     */
    /*public Node getRandomNode(){
        int leftSize = (left == null ? 0 : left.size);      //left size

        Random random = new Random();
        int randomIndex = random.nextInt(leftSize);

        return getIthNode(randomIndex);

        /*if(randomIndex < leftSize){     //random from left
            return left.getRandomNode();
        }else if (randomIndex == leftSize){
            return this;
        }else{
            return right.getRandomNode();
        }
        */
//    }

    public Node getIthNode(int i) {
        int leftSize = left == null ? 0 : left.size;
        if (i < leftSize) {
            return left.getIthNode(i);
        } else if (i == leftSize) {
            return this;
        } else {
            return right.getIthNode(i - (leftSize + 1));
        }
    }

}

class Tree {
    Node root;

    public void insertInOrder(int value) {
        if (root == null) {
            root = new Node(value);
        } else {
            root.insert(value);
        }
    }

    public int size() {
        return root == null ? 0 : root.size;
    }

    /**
     * Get Random Node
     */
    public Node getRandomNode() {
        if (root == null) return null;

        Random random = new Random();           //java.util.Random
        int randomIndex = random.nextInt(size());

        return root.getIthNode(randomIndex);
    }

}

public class RandomNode {

    public static void main(String[] args) {

        Tree tree = new Tree();
        int[] array = {1,2,3,4,5,6,7,8,9,10,11};
        for (int x : array) {
            tree.insertInOrder(x);
        }

        for(int i=0; i < 10; i++) {
            int randomData = tree.getRandomNode().data;
            System.out.println("Random value: " + randomData);
        }

    }
}
