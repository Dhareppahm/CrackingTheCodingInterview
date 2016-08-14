package datastructures.treesgraphs.pathswithsum;

/**
 Problem 4.12: Paths with Sum
 You are given a binary tree in which each node contains integer value (+ve/-ve)
 Design an algorithm to count the no of paths that sum to a given value.

 Note: Path does not need to start or end at leaf
 */
class Node{
    int data;
    Node left;
    Node right;

    public Node(int data){
        this.data = data;
    }
}

class Tree{
    Node root;

    /**
     * Brute Force solution:
     * Balanced tree: O(n log n), Worst case: O(n^2)
     */
    public int countPathsWithSum(Node root, int targetSum){
        if(root == null){
            return 0;
        }

        int pathsFromRoot = helper_CountPathsFromEachNode(root, targetSum, 0);      //count path from each node

        int pathsFromLeft = countPathsWithSum(root.left, targetSum);        //from left subtree
        int pathsFromRight = countPathsWithSum(root.right, targetSum);        //from right subtree

        return pathsFromRoot + pathsFromLeft + pathsFromRight;
    }

    private int helper_CountPathsFromEachNode(Node node, int targetSum, int currentSum) {
        if(node == null)
            return 0;

        currentSum += node.data;

        int paths = 0;
        if(currentSum == targetSum){    //found path from root (node)
            paths++;
        }

        paths += helper_CountPathsFromEachNode(node.left, targetSum, currentSum);
        paths += helper_CountPathsFromEachNode(node.right, targetSum, currentSum);

        return paths;
    }
}

public class PathsWithSum {

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.root = new Node(10);
        tree.root.left = new Node(5);
        tree.root.right = new Node(-3);
        tree.root.left.left = new Node(3);
        tree.root.left.right = new Node(2);
        tree.root.left.left.left = new Node(3);
        tree.root.left.left.right = new Node(-2);
        tree.root.left.right.right = new Node(1);
        tree.root.right.right = new Node(11);

        int paths = tree.countPathsWithSum(tree.root, 8);
        System.out.println("Total paths = "+ paths);
    }
}
