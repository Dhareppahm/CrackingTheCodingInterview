package datastructures.treesgraphs.bstsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 Problem 4.9: BST Sequence
 A BST was created by traversing through an array from left to right and inserting elements. Given a BST with distinct
 elements, print all possible arrays that could have led to this tree
 Eg:
        2
      /  \
     1    3
 Output: {2,1,3} {2,3,1}
 ----------------------------------------------------------------------------------------------------------------------
Approach:
 Two main functions:
 1. allSequences:
    i) Return the List<List<>> of all lists which can be used to construct BST
    ii) Start from root, get sequence for left and right subtree
    iii) Weave together each left sequence with right sequence

 2. weaveLists (first, second, prefix):
    i) Remove head from first list, recurse and then doing the same for second list
    ii) When we recurse we push the prefixed elements down the recursion.
    iii) When first or second is empty, we add the remainder to prefix and store the result.
 */
class Node{
    int data;
    Node left;
    Node right;

    public Node(int data){
        this.data = data;
    }
}

class BinarySearchTree{
    Node root;

    public void displayInorder(Node n){
        if(n != null){
            displayInorder(n.left);
            System.out.print(n.data + " ");
            displayInorder(n.right);
        }
    }


    /**
     * allSequences
     */
    public ArrayList<LinkedList<Integer>> allSequences(Node node){
        ArrayList<LinkedList<Integer>> results = new ArrayList<>();

        if(node == null){
            results.add(new LinkedList<>());
            return results;
        }

        //Prefix list, in which sequence is dumped as soon as either first or second list is empty
        LinkedList<Integer> prefix = new LinkedList<>();
        prefix.add(node.data);

        ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left);       //left subtree sequence
        ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right);     //right subtree sequence

        for(LinkedList<Integer> left : leftSeq){        //Weave each left seq with right seq
            for(LinkedList<Integer> right : rightSeq){
                ArrayList<LinkedList<Integer>> weavedList = new ArrayList<>();
                weaveLists(left, right, weavedList, prefix);
                results.addAll(weavedList);
            }
        }

        return results;
    }

    /**
     * weaveLists
     */
    public void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second,
                            ArrayList<LinkedList<Integer>> weavedList, LinkedList<Integer> prefix) {

        //If either first or second is empty, we add the remainder to prefix and store the result.
        if(first.isEmpty() || second.isEmpty()){
            //Imp to clone the prefix, as we want original prefix back after recursing
            LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();

            result.addAll(first);
            result.addAll(second);
            weavedList.add(result);
            return;
        }

        //Remove head from first list, recurse and then doing the same for second list
        int firstHead = first.removeFirst();
        prefix.addLast(firstHead);
        weaveLists(first, second, weavedList, prefix);      //recurse
        prefix.removeLast();                            //revert back after done with recursing
        first.addFirst(firstHead);

        int secondHead = second.removeFirst();              //Do same for second
        prefix.addLast(secondHead);
        weaveLists(first, second, weavedList, prefix);      //recurse
        prefix.removeLast();
        second.addFirst(secondHead);
    }


}

public class BSTSequence {

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        /**
                    4
                 /    \
                2      6
              /  \    / \
             1   3   5   7

         */
        bst.root = new Node(4);
        bst.root.left = new Node(2);
        bst.root.left.left = new Node(1);
        bst.root.left.right = new Node(3);
        bst.root.right = new Node(6);
        bst.root.right.left = new Node(5);
        bst.root.right.right = new Node(7);

        System.out.println("BST is: ");
        bst.displayInorder(bst.root);

        System.out.println("\nArrays are:");
        ArrayList<LinkedList<Integer>> results = bst.allSequences(bst.root);
        for(LinkedList<Integer> result : results){
            System.out.println(Arrays.toString(result.toArray()));
        }
    }
}
