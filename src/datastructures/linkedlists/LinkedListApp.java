package datastructures.linkedlists;

import java.util.HashSet;
import java.util.Set;

class Link {
	int data;
	Link next;

	public Link(int data){
		this.data = data;
	}

	public void display(){
		System.out.println("data : "+ data);
	}
}

class LinkList{
	Link first;		//The reference to first node in linkedlist is required, as we start traverse etc. from first node/link

	public LinkList(){
		first = null;	//Initally LinkedList is empty, so first = null
	}

	/**
	 * Other basic methods for LinkList
	 */
	public boolean isEmpty(){
		return (first == null);
	}

	public void insertFirst(int data){
		Link newLink = new Link(data);

		newLink.next = first; //newLink -> old first
		first = newLink;	//first -> newLink		
	}

	public void displayList(){
		Link current = first;	//start iterating from first

		while(current != null){
			current.display();
			current = current.next;
		}		
	}

	/*****************************************************************************
	 * Question 2.1)  Remove Duplicates : 2 approaches
	 * 
	 *****************************************************************************/
	//Approach 1 : Using a temporary buffer : O(n) but has space complexity
	public void removeDuplicatesUsingHashSet() {
		Link current = first;
		Link previous = first;
		Set<Integer> hashSet = new HashSet<Integer>();

		while(current != null) {

			//flag will be false, if duplicate, so hashSet did not add data
			boolean flag = hashSet.add(current.data);

			if(!flag){
				//delete duplicate element
				if(current.next == null){
					previous.next = null;					
				}else{				
					previous.next = current.next;
				}
			}else{
				previous = current;	//set previous to current only if not duplicate
			}			
			current = current.next;	//move current forward
		}
	}	
	
	
	//Approach 2 : Here we use two pointers, current and runner to loop through the 
	//list and remove duplicates. Current value is checked with Runner value.
	//Complexity is : O(n*2)
	public void removeDuplicatesWithoutBuffer() {
		
		//Initialize the pointers
		Link current = first;
		while(current != null){			
			Link runner = current;
			
			while(runner.next != null){				
				if(current.data == runner.next.data){
					//remove the runner link(data)
					runner.next = runner.next.next;					
				}else{				
					runner = runner.next;
				}
			}			
			current = current.next;
		}		
	}
	
	
	/******************************************************************************
	 * Question 2.2) Implement an algorithm to find the kth to last element 
	 *  			 of a singly linked list.
	 *   
	 ******************************************************************************/
	//Approach : Create 2 pointers, Current and Runner and start running them with 
	//a difference of K. Current will start from first and Runner will start K nodes ahead
	//of Current. Increment both pointers. When Runner will reach end of List, 
	//Current should point to Node we require. 
	
	public void findKthLastElement(int k){
		Link current = first;
		Link runner = first;
		int diff = k;
		//Move Runner K links ahead
		while(k >= 0) {
			runner = runner.next;
			k--;
		}
		
		//System.out.println("Current is : "+ current.data);
		//System.out.println("Runner is : "+ runner.data);
		
		//Loop and move both pointers ahead
		while(runner != null){
			runner = runner.next;
			current = current.next;
		}
		//When runner reaches end of list, the current pointer should point to Kth to last link
		System.out.println(diff + "th last element is :" + current.data);
		
	}
	
	/***********************************************************************************
	 * Question 2.3) Algorithm to delete a node in the middle of a singly linked list,
					given only access to that node
	 * 
	 ***********************************************************************************/
	//Approach : Start from beginning and goto till we find the Link to delete.
	//Change the previous link pointer to point to current's next pointer
	public void deleteLink(int deleteData){
		Link current = first;
		Link previous = first;
		
		while(current != null){
			if(first.data == deleteData){
				//move first ahead
				first = current.next;				
			}else if(current.data == deleteData){				
				previous.next = current.next;				
			}else{
				previous = current;
			}
			current = current.next;
		}
	}
	
	/***********************************************************************************
	 * Question 2.4) Partition a linked list around a value x, such that all nodes less than
					x come before all nodes greater than or equal to x.
	 * 
	 ***********************************************************************************/
	//Approach : Create 2 list pointers (before, after), one with elements less than value x. second with values
	//less than or equal to value x. After we go through all the elements, merge the two lists
	public void partitionList(int partitionValue){
		
		//Current List head/first pointer
		Link current = first;
		
		//New List pointers
		Link beforeStart = null;		//stores values less than partition
		Link beforeEnd = null;
		
		Link afterStart = null;			//stores values greater than partition
		Link afterEnd = null;
		
		//iterate through our original list
		while(current != null) {
			Link newLink = new Link(current.data);
			
			if(current.data < partitionValue) {				//add to  belowList
				if(beforeStart == null) {
					beforeStart = newLink;
					beforeEnd = beforeStart;
				}else {
					beforeEnd.next = newLink;
					beforeEnd = newLink;
				}				
			} else {											//add to aboveList
				if(afterStart == null){
					afterStart = newLink;
					afterEnd = afterStart;
				}else{
					afterEnd.next = newLink;
					afterEnd = newLink;
				}				
			}			
			current = current.next;
		}
		
		//Merge the lists
		if(beforeStart == null){
			beforeStart = afterStart;
		}		
		beforeEnd.next = afterStart;	//Point beforeEnd next to afterStart
		
		//Display New List
		System.out.println("New List after partition : ");
		while(beforeStart != null) {
			System.out.println(beforeStart.data);
			beforeStart = beforeStart.next;
		}
	}
	
	
}


public class LinkedListApp {

	public static void main(String[] args) {
		LinkList list = new LinkList();

		list.insertFirst(20);
		list.insertFirst(15);
		list.insertFirst(100);
		list.insertFirst(40);
		list.insertFirst(25);
		list.insertFirst(35);
		list.insertFirst(30);
		list.insertFirst(10);
		list.insertFirst(10);

		System.out.println("Initial list :");
		list.displayList();

		//remove duplicate links
		System.out.println("List after duplicates removed :");
		//list.removeDuplicatesUsingHashSet();
		list.removeDuplicatesWithoutBuffer();
		list.displayList();
		
		//Find Kth to last element
		list.findKthLastElement(3);

		//Delete Link 
		int deleteData = 35;
		System.out.println("List after deletion of : "+deleteData);
		list.deleteLink(deleteData);
		list.displayList();
		
		//
		list.partitionList(30);
		
	}

}
