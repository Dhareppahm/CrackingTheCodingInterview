package datastructures.trees;

import java.util.LinkedList;
import java.util.Stack;

import javax.security.auth.login.CredentialException;

/**
 * Represents each Node. Each Node can have a data, left child, right child
 */
class Node{
	int data;
	Node left;
	Node right;

	public Node(int data){
		this.data = data;
	}	
	public void display(){
		System.out.println(" " + this.data);
	}
}

class Tree{
	private Node root;		//this is required to traversal etc. as we start from root.

	public Tree(){
		root = null; 		//initially the tree is empty, i.e root = null
	}
	
	public Node getRoot(){
		return root;
	}

	/**
	 * Insert Node into Binary Search Tree. 
	 * BST : Node to Left is less than Parent, Node to Right is greater than Parent
	 */
	public void insert(int data){
		Node newNode = new Node(data);
		if(root == null){					//If Tree is empty
			root = newNode;
		} else {							//If tree is not empty
			Node current = root;
			Node parent;					//Parent is similar to previous pointer used in Linkedlist

			while(true){
				parent = current;

				if(data < current.data) {			//Left child
					current = current.left;
					if(current == null){			//go till the leaf node on left
						parent.left = newNode;		//then add the newNode
						return;
					}					
				} else if(data > current.data) {		//Right child	
					current = current.right;		//go till the leaf node on right
					if(current == null){
						parent.right = newNode;		//add the newNode
						return;
					}							
				}

			}//end while			

		}
	}
	
	/**
	 * BST Tree Traversal : 3 types of Traversals - InOrder, PreOrder, PostOrder
	 */
	public void traverse(int traversalType){
		switch(traversalType){
			case 1 : inOrder(root); break;
			case 2 : preOrder(root);	break;
			case 3 : postOrder(root); break;
		}
	}
	
	private void inOrder(Node localRoot){		
		if(localRoot != null){
			inOrder(localRoot.left);				//left
			System.out.print(localRoot.data + " ");		//root
			inOrder(localRoot.right);				//right
		}		
	}
	
	private void preOrder(Node localRoot){		
		if(localRoot != null){
			System.out.print(localRoot.data + " ");		//root
			preOrder(localRoot.left);				//left			
			preOrder(localRoot.right);				//right
		}		
	}
	
	private void postOrder(Node localRoot){		
		if(localRoot != null){			
			postOrder(localRoot.left);				//left			
			postOrder(localRoot.right);				//right
			System.out.print(localRoot.data + " ");		//root
		}		
	}
	
	
	/**
	 * 4.3 Given a sorted (increasing order) array with unique integer elements, write an algorithm to create a binary search tree with minimal height.
	 */
	public Node createMinimalBST(int arr[], int start, int end){		
		//base condition
		if(end < start){
			return null;
		}
		

		int mid = (start + end)/2;
		Node newNode = new Node(arr[mid]);
		
		if(root == null){
			root = newNode;
		}		
		newNode.left = createMinimalBST(arr, start, mid - 1);
		newNode.right = createMinimalBST(arr, mid + 1, end);
		return newNode;
	}
	
	/**
	 * BFS for Tree : Key thing is BFS is interative and uses a QUEUE. (In case of Graphs, we Mark nodes visited as True as Graph can have cycles)
	 */
	public void BFS() {
		//This operates as a Queue
		LinkedList<Node> queue = new LinkedList<Node>();
		
		if(root == null){
			return;
		}
		
		queue.add(root);	//add root to queue
		while(!queue.isEmpty()){
			Node n = queue.remove();		//remove the node from Queue
				
			System.out.print(n.data + " ");	//print the node
			if(n.left != null){				
				queue.add(n.left);				//if left node present add it to queue. We add level by level so will be removed from queue, level by level
			}
			if(n.right != null){				//if right node present add it to queue.
				queue.add(n.right);
			}			
		}		
	}
	
	
	/**
	 * DFS of Tree : DFS we implement using STACK or RECURSION.  
	 */
	public void DFS_usingStack(){
		//Iterative solution using a Stack
		Stack<Node> stack = new Stack<Node>();
		
		if(root == null){
			return;
		}
		
		stack.push(root);
		while(!stack.isEmpty()){
			Node n = stack.pop();			//remove the node from Stack
			
			System.out.print(n.data + " ");	//print the node
			if(n.right != null){				//if right node present add it to stack. ------- Difference with BFS : We push right first, then left in Stack as LIFO
				stack.add(n.right);
			}
			if(n.left != null){				
				stack.add(n.left);				//if left node present add it to stack.
			}
			
		}
	}
	
	/**
	 * DFS : Using Recursion
	 */
	public void DFS_recursion(Node root){
		//base condition
		if(root == null){
			return;
		}		
		System.out.print(root.data + " ");
		DFS_recursion(root.left);
		DFS_recursion(root.right);
	}

}//end class Tree


public class TreeApp {

	public static void main(String[] args) {

		System.out.println("Start");
		Tree tree = new Tree();
		
		tree.insert(40);
		tree.insert(20);
		tree.insert(50);
		tree.insert(60);
		tree.insert(30);
		tree.insert(10);
		
		System.out.println("\nInorder :");
		tree.traverse(1);
		System.out.println("\nPreorder :");
		tree.traverse(2);
		System.out.println("\nPostorder :");
		tree.traverse(3);

		
		Tree ctciTree = new Tree();
		System.out.println("\nCTCI 4.3 : Create Minimal BST :");
		int[] arr = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150};
		ctciTree.createMinimalBST(arr, 0, arr.length - 1);
		
		System.out.println("\nPreorder :");
		ctciTree.traverse(2);
		
		System.out.println("\nBFS (uses Queue):");
		ctciTree.BFS();
		
		System.out.println("\nDFS (using Stack) :");
		ctciTree.DFS_usingStack();
		
		System.out.println("\nDFS (using Recursion) :");
		ctciTree.DFS_recursion(ctciTree.getRoot());


	}

}











