package datastructures.treesgraphs.checkbalanced;

/**
 Problem 4: Check if tree Balanced
 Implement a function to check if tree is balanced.
 Balanced tree - is defined to be a tree such that heights of two subtrees never differ by more than 1

 See also: leetcode.easy.tree.balanced
 */

class Node{
    int data;
    Node left;
    Node right;

    public Node(int data){
        this.data = data;
    }
}

class Tree {
    Node root;        //this is required to traversal etc. as we start from root.

    public Tree() {
        root = null;        //initially the tree is empty, i.e root = null
    }

    public void displayIndorder(Node root){
        if(root != null){
            displayIndorder(root.left);
            System.out.print(root.data + " ");
            displayIndorder(root.right);
        }
    }

    public boolean isBalanced(Node root){
        int height = helper(root);

        if(height == Integer.MIN_VALUE){
            return false;
        }else{
            return true;
        }
    }

    private int helper(Node root){
        if(root == null){       //base condition
            return -1;
        }

        int leftHeight = helper(root.left);
        if(leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;       //if error return error right away

        int rightHeight = helper(root.right);
        if(rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;       //if error return error right away

        if(Math.abs(leftHeight - rightHeight) > 1){
            return Integer.MIN_VALUE;                   //return error MIN_VALUE
        } else {
            return Math.max(leftHeight, rightHeight) + 1;   //return height which is max of left and right subtree
        }
    }

}


public class CheckIfTreeBalanced {
    public static void main(String[] args) {
        Tree tree = createTree();
        tree.displayIndorder(tree.root);

        boolean result = tree.isBalanced(tree.root);
        System.out.println("\nIs Balanced : " + result);
    }

    private static Tree createTree() {
        Tree tree = new Tree();
        /**
               1
             /   \
            2     3
          / \    / \
         4   5   6  7
         */
        tree.root = new Node(1);
        tree.root.left = new Node(2);
//        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
//        tree.root.right.left = new Node(6);
//        tree.root.right.right = new Node(7);

        return tree;
    }
}

