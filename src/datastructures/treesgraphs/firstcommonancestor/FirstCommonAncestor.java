package datastructures.treesgraphs.firstcommonancestor;

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

 Approach 1:


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

    public void displayInorder(Node n){
        if(n != null){
            displayInorder(n.left);
            System.out.print(n.data + " ");
            displayInorder(n.right);
        }
    }

    /**
     * By Recursion
     */
    public Node findLCA(Node node, int p, int q){
        if(node == null) {
            return null;
        }

        if(node.data == p || node.data == q){
            return node;
        }

        //Check left and right subtrees
        Node leftLCA = findLCA(node.left, p, q);
        if(leftLCA != null && leftLCA.data != p && leftLCA.data != q){            //return lca right away
            return leftLCA;
        }

        Node rightLCA = findLCA(node.right, p, q);
        if(rightLCA != null && rightLCA.data != p && rightLCA.data != q){            //return lca right away
            return rightLCA;
        }

        //one node is present in once subtree and other is present in other. So this node is the LCA
        if(leftLCA != null && rightLCA != null){
            return node;
        }
        //If both nodes are in a left subtree of right subtree. Return one which is not null
        return (leftLCA != null) ? leftLCA : rightLCA;
    }



    public boolean isNodePresent(Node node, int value) {
        if(node == null){               //base condition
            return false;
        }

        if(node.data == value){         //if value match, return true
            return true;
        }

        boolean found = isNodePresent(node.left, value);
        if(!found){                                             //not found in left, then check in right
            found = isNodePresent(node.right, value);
        }
        return found;
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
        int q = 15;

        boolean nodesPresent = tree.isNodePresent(tree.root, p) && tree.isNodePresent(tree.root, q);
        if(nodesPresent){
            Node lca = tree.findLCA(tree.root, p, q);
            System.out.println("\nLCA is : "+ lca.data);
        }else{
            System.out.println("\nError: Nodes not present");
        }

    }

}
