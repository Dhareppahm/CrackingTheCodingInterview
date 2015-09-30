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
	Link first;

	public LinkList(){
		first = null;
	}

	//Other basic methods for LinkList
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

	/**
	 * Remove Duplicates : 2 approaches
	 */
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
	
}


public class RemoveDuplicates {

	public static void main(String[] args) {
		LinkList list = new LinkList();

		list.insertFirst(50);
		list.insertFirst(40);
		list.insertFirst(30);
		list.insertFirst(40);
		list.insertFirst(20);
		list.insertFirst(50);

		System.out.println("Initial list :");
		list.displayList();

		//remove duplicate links
		System.out.println("List after duplicates removed :");
		//list.removeDuplicatesUsingHashSet();
		list.removeDuplicatesWithoutBuffer();
		list.displayList();

	}

}
