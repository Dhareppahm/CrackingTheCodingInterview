package datastructures.linkedlists.doubly;

class Link {
	int data;
	Link next = null;
	Link prev = null;

	public Link(int data){
		this.data = data;
	}

	public void displayLink(){
		System.out.println(" data : "+ data);
	}
}

class LinkList {
	Link first;
	Link last;

	public LinkList(){
		first = null;	//initialize first to null
		last = null;	//initialize last to null
	}

	public boolean isEmpty(){
		return (first == null);
	}

	//============================INSERT METHODS==================================
	/**
	 * Insert a new Link at start of LinkList. First should point to this element
	 */
	public void insertFirst(int data) {
		Link newLink = new Link(data);

		if(isEmpty()) {
			last = newLink;
		}else {
			first.prev = newLink;
		}
		newLink.next = first;	//newlink -> old first
		first = newLink;		//first -> newlink
	}

	/**
	 * Insert a new Link at end of LinkList. Last should point to this element
	 */
	public void insertLast(int data) {		
		Link newLink = new Link(data);

		if(isEmpty()){
			first = newLink;			
		}else{
			newLink.prev = last;
			last.next = newLink;			
		}		
		last = newLink;		
	}

	/**
	 * Insert new link after the key
	 */
	public void insertAfter(int key, int data){

		//start from first and find the key. Then insert newLink after it
		Link current = first;

		while(current != null){
			if(current.data == key){

				System.out.println("inserting after : "+ current.data);
				//Now create a newLink to insert after current
				Link newLink = new Link(data);

				//if insert after last element
				if(current == last){
					newLink.next = null;
					newLink.prev = current;
					current.next = newLink;
					last = newLink;					
				}else{				
					//if insert between
					newLink.next = current.next;
					current.next.prev = newLink;
					current.next = newLink;
					newLink.prev = current;
				}
				break;

			}else{
				current = current.next;
			}
		}
	}


	//============================DELETE METHODS==================================

	/**
	 * Delete the first link in LinkList
	 */
	public Link deleteFirst(){
		Link temp = first;
		first.next.prev = null;
		first = first.next;	//move first forward
		return temp;	//temp i.e olf first is no longer referred and will be garbage collected
	}

	/**
	 * Delete the last link
	 */
	public Link deleteLast(){
		Link temp = last;
		last.prev.next = null;
		last = last.prev;	//move last to previous link		
		return temp;
	}

	public Link deleteLink(int key){
		Link current = first;

		while(current != null){

			if(current.data == key){

				if(current == first){
					first = current.next;
				}else if(current == last){
					last = current.prev;
					last.next = null;
				}else{
					current.prev.next  = current.next;
					current.next.prev = current.prev;
				}
				break;
			}

			current = current.next;
		}
		return current;
	}

	//============================TRAVERSAL METHODS==================================

	/**
	 * Start from first and move on to next elements in the list and print each list element
	 */
	public void displayForward(){

		System.out.println("Display FORWARD : from First -> Last ");
		Link current = first;

		while(current != null){
			current.displayLink();
			current = current.next;		//move current forward
		}
	}


	public void displayBackward() {		
		System.out.println("Display BACKWARD : from Last -> First ");

		Link current = last;			
		while(current != null){				
			current.displayLink();				
			current = current.prev;				
		}			
	}	

	//========================FINDING METHODS=================================

	/**
	 * Find a Link with given data value, if found return link else return null
	 */
	/*public Link findLink(int data){
		Link current = first;

		while(current != null){
			if(current.data == data){
				return current;
			}else{
				current = current.next;
			}
		}

		return current;
	}*/


}

public class DoublyLinkedList {

	public static void main(String[] args) {

		LinkList list = new LinkList();

		list.insertLast(10);
		list.insertLast(20);
		list.insertLast(30);
		list.insertLast(40);
		list.insertLast(50);
		list.insertLast(60);

		list.insertAfter(60,100);

		/*list.insertFirst(60);
		list.insertFirst(50);
		list.insertFirst(40);
		list.insertFirst(30);
		list.insertFirst(20);
		list.insertFirst(10);*/

		list.displayForward();
		list.displayBackward();

		System.out.println("Delete first");
		list.deleteFirst();
		list.displayForward();

		System.out.println("Delete last");
		list.deleteLast();
		list.displayForward();

		System.out.println("Delete Key");
		list.deleteLink(60);
		list.displayForward();

	}

}
