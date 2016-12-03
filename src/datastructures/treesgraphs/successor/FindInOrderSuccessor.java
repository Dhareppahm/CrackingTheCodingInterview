package datastructures.treesgraphs.successor;

/**
 Problem 6: Successor
 Write an algorithm to find the "next" node i.e Inorder successor of a given node in a BST. You may assume that each node
 has a link to its parent

 -----------------------------------------------------------------------------------------------------------------------
 Approach:
  if(n has right subtree){
    return leftmost child of n
 }else{

    while(n is right child of n.parent){
        n = n.parent;       //Go Up
    }
    return n.parent;        //return parent as its not been traversed and is next to be traversed
 }
 */


//need to have link to parent node too trace back
class Node{
    int data;
    Node left;
    Node right;
    Node parent;

    public Node(int data){
        this.data = data;
    }
}

class Tree{
    Node root;

    public Node inorderSucc(Node n){
        if(n == null)
            return null;

        //n has right subtree, return leftmost child
        if(n.right != null){
            return getLeftMostNode(n.right);
        }else{
            //n is right child of n.parent, go up
            Node q = n;
            Node temp = q.parent;
            while(temp != null && temp.left != q){        //go up until we're on left instead of right
                q = temp;
                temp = temp.parent;
            }
            return temp;
        }
    }

    private Node getLeftMostNode(Node n) {
        if(n == null)
            return null;

        while(n.left != null){      //like likedlist traversal
            n = n.left;
        }
        return n;
    }
}


public class FindInOrderSuccessor {

    public static void main(String[] args) {
        Tree tree = new Tree();
        /**
                4
             /     \
            2       6
         /   \     /
        1    3    5

         */

        //Also set parent here, as in this problem we need to backtrack and go up
        tree.root = new Node(4);
        tree.root.left = new Node(2);   tree.root.left.parent = tree.root;
        tree.root.right = new Node(6);   tree.root.right.parent = tree.root;

        tree.root.left.left = new Node(1);  tree.root.left.left.parent = tree.root.left;
        tree.root.left.right = new Node(3);  tree.root.left.right.parent = tree.root.left;

        tree.root.right.left = new Node(5); tree.root.right.left.parent = tree.root.right;

        //Find inorder successor
        Node n = tree.root.left.right;

        Node next = tree.inorderSucc(n);
        if(next != null) {
            System.out.println(n.data + " -> " + next.data);
        }else{
            System.out.println(n.data + " -> "+ null);
        }

    }
}
