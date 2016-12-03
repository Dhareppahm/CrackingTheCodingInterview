package datastructures.treesgraphs.listofdepths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 Problem 3 Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth
 (e.g., if you have a tree with depth D, you'll have D linked lists).

 */
class Node {
    int data;
    Node left;
    Node right;

    public Node(int data){
        this.data = data;
    }

    @Override
    public String toString() {
        return Integer.toString(data);
    }
}

class Tree{
    Node root;

    public void displayInorder(Node root){
        if(root != null){
            displayInorder(root.left);
            System.out.print(root.data + " ");
            displayInorder(root.right);
        }
    }


    public List<List<Node>> BFS_LevelLists(Node root){
        List<List<Node>> levelLists = new ArrayList<>();
        LinkedList<Node> current = new LinkedList<>();        //used like a Queue

        if(root != null) {
            current.add(root);            //add root into queue
        }

        while(!current.isEmpty()) {

            levelLists.add(current);        //add previous level
            List<Node> parents = current;    //assign current level to parent and go to next level
            current = new LinkedList<>();   //next level start empty

            for(Node n : parents){
                if(n.left != null) {
                    current.add(n.left);
                }
                if(n.right != null) {
                    current.add(n.right);
                }
            }
        }

        return levelLists;
    }


    /*
    //Modification to Recursive solution of DFS
    public LinkedList<LinkedList<Node>> createListAtEachLevel_byDFS(Node root, LinkedList<LinkedList<Node>> listsAtEachLevel, int level){
        //base condition
        if(root == null){
            return null;
        }
        LinkedList<Node> list = null;

        //This if-else is the main logic for maintaining Level wise List
        if(listsAtEachLevel.size() == level){		//level not in lists, so create a new list and add it
            list = new LinkedList<Node>();
            listsAtEachLevel.add(list);
        }else{
            list = listsAtEachLevel.get(level);		//get the list at particular level
        }

        list.add(root);
        createListAtEachLevel_byDFS(root.left, listsAtEachLevel, level + 1);
        createListAtEachLevel_byDFS(root.right, listsAtEachLevel, level + 1);

        return listsAtEachLevel;
    }

    //Modification to Iterative solution of BFS_LevelLists
    public LinkedList<LinkedList<Node>> createListAtEachLevel_byBFS(LinkedList<LinkedList<Node>> listsAtEachLevel){
        //This operates as a Queue
        LinkedList<Node> queue = new LinkedList<Node>();

        //tree empty
        if(root == null){
            return null;
        }

        queue.add(root);
        LinkedList<Node> list = null;
        int level = 0;

        while(!queue.isEmpty()){
            Node n = queue.remove();

            //This if-else is the main logic for maintaining Level wise List
            if(listsAtEachLevel.size() == level){		//as we go on incrementing the levels, we add a new list to listsAtEachLevel.
                list = new LinkedList<Node>();
                listsAtEachLevel.add(list);
            }else{										//then once all levels are reached, we come to else part and get the list at particular level
                list = listsAtEachLevel.get(level);
            }
            list.add(n);

            System.out.println(n.data + " ");
            if(n.left != null){
                queue.add(n.left);
            }
            if(n.right != null){
                queue.add(n.right);
            }

            //increment level
            if(n.left != null || n.right != null){
                level++;
            }

        }

        return listsAtEachLevel;
    }*/
}


public class ListOfDepths {

    public static void main(String[] args) {
        Tree tree = createTree();

        System.out.println("Tree: ");
        tree.displayInorder(tree.root);

        System.out.println("\nBFS_LevelLists traversal: ");
        List<List<Node>> levelLists = tree.BFS_LevelLists(tree.root);

        int level = 0;
        for(List<Node> list : levelLists){
            System.out.println("Level: "+ level++ + Arrays.toString(list.toArray()));
        }
    }

    private static Tree createTree() {
        Tree tree = new Tree();

        /**
                 1
               /   \
             2      3
            / \    / \
           4   5   6  7
          /\
         8  9

         */
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.left.left.left = new Node(8);
        tree.root.left.left.right = new Node(9);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);

        return tree;
    }
}
