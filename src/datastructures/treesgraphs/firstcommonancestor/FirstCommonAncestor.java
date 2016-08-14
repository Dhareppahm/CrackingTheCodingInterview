package datastructures.treesgraphs.firstcommonancestor;

import java.util.Arrays;
import java.util.LinkedList;

/**
 Problem 8: First Common Ancestor
 Design an algorithm and write code to find the "First Common Ancestor" of two nodes in Binary tree.

 Avoid storing additional nodes in a data structure.

 NOTE: This is not necessarily BST
 -----------------------------------------------------------------------------------------------------------------------
### Note:
 LCA for BST : Making use of BST property
 http://www.geeksforgeeks.org/lowest-common-ancestor-in-a-binary-search-tree/
 1. If both n1 and n2 are smaller than root, then LCA lies in left
 2. If both n1 and n2 are greater than root, then LCA lies in right
 3. Else LCA is the node itself

 Time Complexity: O(h), h = height of tree

### If Tree is not BST: http://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/

 Approach 1: Recursion
 1) Traverse the tree starting from root. If any of the given nodes (n1 and n2) matches with root, then root is LCA.
 2) If root doesn't match with any of the nodes, we recur for left and right subtrees.
 The node which has n1 present in its one subtree and the n2 present in other subtree is the LCA.
 3) If both nodes lie in left subtree, then left subtree has LCA, otherwise LCA lies in right subtree.

Approach 2: Iterative
 1) Find path from root to n1 and store it in LinkedList<Node>.
 2) Find path from root to n2 and store it in another LinkedList<Node>.
 3) Traverse both paths till the values in list are same. Return the common node just before the mismatch.
 */
class Node{
    int data;
    Node left;
    Node right;

    public Node(int data){
        this.data = data;
    }

    @Override
    public String toString(){
        return Integer.toString(data);
    }
}

class Tree{
    Node root;

    public void displayInorder(Node n){
        if(n != null){
            displayInorder(n.left);
            System.out.print(n.data + " ");
            displayInorder(n.right);
        }
    }

    /******************************************************************
     * By Recursion
     ******************************************************************/
    public Node findLCA(Node node, int p, int q){
        if(node == null) {    //base condition
            return null;
        }

        if(node.data == p || node.data == q){   //check condition
            return node;
        }

        //Recurse to left and right subtrees
        Node leftLCA = findLCA(node.left, p, q);
        if(leftLCA != null && leftLCA.data != p && leftLCA.data != q){            //optional: return lca right away
            return leftLCA;
        }

        Node rightLCA = findLCA(node.right, p, q);
        if(rightLCA != null && rightLCA.data != p && rightLCA.data != q){            //optional: return lca right away
            return rightLCA;
        }

        //one node is present in once subtree and other is present in other. So LCA is their parent
        if(leftLCA != null && rightLCA != null){
            return node;
        }
        //If both nodes are in a left subtree of right subtree. Return one which is not null
        return (leftLCA != null) ? leftLCA : rightLCA;
    }

    /**
     * Utility to check if both the Nodes present in Tree or not. Don't find LCA is node not present
     */
    public boolean isNodePresent(Node node, int value) {
        if(node == null){               //base condition
            return false;
        }

        if(node.data == value){         //check condition: if value match, return true
            return true;
        }

        boolean found = isNodePresent(node.left, value);        //recurse left and then right
        if(!found){                                             //not found in left, then check in right
            found = isNodePresent(node.right, value);
        }
        return found;
    }

    /************************************************************
     * Iterative
     ************************************************************/
    public Node findLCA_1(Node root, int node1, int node2){

        LinkedList<Node> pathToNode1 = new LinkedList<>();
        LinkedList<Node> pathToNode2 = new LinkedList<>();

        boolean pathToNode1Exists = findPath(root, pathToNode1, node1);
        boolean pathToNode2Exists = findPath(root, pathToNode2, node2);

        if(pathToNode1Exists){
            System.out.println("Path to "+node1);
            System.out.println(Arrays.toString(pathToNode1.toArray()));
        }
        if(pathToNode2Exists){
            System.out.println("Path to "+node2);
            System.out.println(Arrays.toString(pathToNode2.toArray()));
        }

        //Find last common node before mismatch
        Node lca = null;
        for(int i=0; i < pathToNode1.size() && i < pathToNode2.size(); i++){
            if(pathToNode1.get(i).data != pathToNode2.get(i).data){         //mismatch
                lca = pathToNode1.get(i-1);
                break;
            }
        }

        return lca;
    }

    private boolean findPath(Node node, LinkedList<Node> path, int val){
        if(node == null){       //base condition
            return false;
        }

        path.add(node);
        if(node.data == val){   //check node present
            return true;
        }

        if(findPath(node.left, path, val) || findPath(node.right, path, val)){     //Recurse left and right
            return true;
        }

        path.removeLast();          //IMP: If not in path, remove last added node
        return false;
    }

}

public class FirstCommonAncestor {

    public static void main(String[] args) {
        Tree tree = new Tree();
        /**
                    1
                /       \
               2         3
             /  \       / \
            4    5     6   7
           / \
         8    9

         */
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);
        tree.root.left.left.left = new Node(8);
        tree.root.left.left.right = new Node(9);

        System.out.println("Tree is: ");
        tree.displayInorder(tree.root);

        //To handle case, when p or q not present, first check if they are present.
        int p = 9;
        int q = 5;

        //By Recursion
        boolean nodesPresent = tree.isNodePresent(tree.root, p) && tree.isNodePresent(tree.root, q);
        if(nodesPresent){
            Node lca = tree.findLCA(tree.root, p, q);
            System.out.println("\nLCA (Recursion) is : "+ lca.data);
        }else{
            System.out.println("\nError: Nodes not present");
        }

        //Iterative
        Node lca = tree.findLCA_1(tree.root, p, q);
        System.out.println("\nLCA (Iterative) is: "+ lca.data);
    }

}
