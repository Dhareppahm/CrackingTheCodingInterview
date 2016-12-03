package datastructures.treesgraphs.checksubtree;

/**
 Problem 4.10: Check Subtree
 T1 and T2 are two very large binary trees, with T1 much bigger than T2. Create an algorithm to determine if T2 is subtree of T1
 -----------------------------------------------------------------------------------------------------------------------
 Approach 1: (Check substring in Tree Traversal String)
 1. Inorder or Preorder: Inorder will not work as it will print sorted order in case of BST and irrespective of structure of tree
 2. Preorder: But with the fix that we print X for null, so we can track exact structure
 3. s1 = Preorder traversal string for T1,
    s2 = Preorder traversal string for T2
 Check if s2 is substring of s1
 Time Complexity: O(m+n)    Space Complexity: O(m+n)

  Approach 2:
 1. Check if root of T2 is present as node in T1.
 2. After match found, recursively check left and right subtrees of T1 and T2 from that match node onwards
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
     * By checking Preorder traversals
     */
    public boolean checkSubtree(Node node1, Node node2){
        String s1 = getPreorderString(node1, new StringBuilder());
        String s2 = getPreorderString(node2, new StringBuilder());

        System.out.println("S1 = "+ s1);
        System.out.println("S2 = "+ s2);

        return s1.indexOf(s2) != -1;        //if no match then return -1 so will return false
    }

    private String getPreorderString(Node node, StringBuilder sb){         //V-L-R (Root-Left-Right)

        sb.append(node == null ? "X" : node.data);
        if(node != null) {
            getPreorderString(node.left, sb);
            getPreorderString(node.right, sb);
        }
        return sb.toString();
    }


    /**
     * By checking subtree after root of T2 matches node in T1
     */
    public boolean checkSubtree_1(Node n1, Node n2){
        if(n2 == null)          //empty tree is subtree
            return true;

        return helper(n1, n2);
    }

    private boolean helper(Node n1, Node n2) {
        if(n1 == null){         //main tree empty, so no match
            return false;
        }else if(n1.data == n2.data){       //once we get match, check together
            return matchTree(n1,n2);
        }

        return helper(n1.left, n2) || helper(n1.right, n2);     //check with T1 left subtree and T1 right subtree
    }

    private boolean matchTree(Node n1, Node n2) {
        if(n1 == null && n2 == null){
            return true;                    //nothing left, so true
        }else if(n1 ==  null || n2 == null){
            return false;                   //either is empty, so no match
        }else if(n1.data != n2.data){
            return false;                   //mismatch
        }else{
            return matchTree(n1.left, n2.left) && matchTree(n1.right, n2.right);    //check both trees left and right
        }
    }

}


public class CheckSubtree {

    public static void main(String[] args) {
        Tree t1 = new Tree();
        Tree t2 = new Tree();
        /**
                3           3
              /              \
             4                4
         */
        t1.root = new Node(3);
        t1.root.left = new Node(4);

        t2.root = new Node(3);
        t2.root.right = new Node(4);

        boolean isSubtree = t1.checkSubtree(t1.root, t2.root);
        System.out.println("By Preorder string check: "+isSubtree);

        isSubtree = t1.checkSubtree_1(t1.root, t2.root);
        System.out.println("By Recursion: " + isSubtree);
    }
}
