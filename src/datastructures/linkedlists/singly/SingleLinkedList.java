package datastructures.linkedlists.singly;

//page 190 : DS&A - Robert Lafore example

class Link {
	int data;
	Link next = null;

	public Link(int data){
		this.data = data;
	}

	public void displayLink(){
		System.out.println(" data : "+ data);
	}
}

class LinkList {
	Link first;

	public LinkList(){
		first = null;	//initialize first to null
	}

	public boolean isEmpty(){
		return (first == null);
	}

	/**
	 * Insert a new Link at start of LinkList. First should point to this element
	 */
	public void insertFirst(int data){
		Link newLink = new Link(data);
		newLink.next = first;	//newlink -> old first
		first = newLink;		//first -> newlink
	}

	/**
	 * Delete the first link in LinkList
	 */
	public Link deleteFirst(){
		Link temp = first;
		first = first.next;	//move first forward
		return temp;	//temp i.e olf first is no longer referred and will be garbage collected
	}

	/**
	 * Start from first and move on to next elements in the list and print each list element
	 */
	public void displayList(){

		System.out.println("Display LinkList : from First -> Last ");
		Link current = first;

		while(current != null){
			current.displayLink();
			current = current.next;		//move current forward
		}
	}

	/**
	 * Find a Link with given data value, if found return link else return null
	 */
	public Link findLink(int data){
		Link current = first;

		while(current != null){
			if(current.data == data){
				return current;
			}else{
				current = current.next;
			}
		}

		return current;
	}


	/**
	 * keep track of 2 links, current and previous.
	 */
	public Link deleteLink(int data){
		//Initialize both current and previous to first as we start from first 
		Link current = first;
		Link previous = first;

		while(current != null){
			if(current.data == data){
				
				//If deleteLink is first
				if(current == first){
					first = current.next;	//move first to next link
					return current;
					
				}else{				
					//If deleteLink is not first
					Link deleteLink = current;
					previous.next = current.next;
					return deleteLink;
				}

			}else{
				previous = current;
				current = current.next;				
			}
		}

		return current;
	}

}

public class SingleLinkedList {

	public static void main(String[] args) {

		LinkList list = new LinkList();

		list.insertFirst(50);
		list.insertFirst(40);
		list.insertFirst(30);
		list.insertFirst(20);
		list.insertFirst(10);

		list.displayList();

		//Delete first link
		list.deleteFirst();

		list.displayList();

		//Find link with value 30
		System.out.println("Find : " + 90);
		Link tempLink = list.findLink(90);
		if(tempLink != null){
			tempLink.displayLink();
		}else{
			System.out.println("Link not found");
		}


		//Delete a particular link in LinkList 
		//Delete 30
		System.out.println("Delete :"+ 50);
		Link delLink = list.deleteLink(50);
		delLink.displayLink();

		list.displayList();

	}

}
