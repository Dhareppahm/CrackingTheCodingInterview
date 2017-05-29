package datastructures.linkedlists.partition;

/**
 Problem 2.4) Partition a linked list around a value x, such that all nodes less than
 x come before all nodes greater than or equal to x.
 */


class Node {
    int data;
    Node next;

    public Node(int data){
        this.data = data;
    }
}

class LinkedList{
    Node head;		//The reference to first node in linkedlist is required, as we start traverse etc. from first node/link

    public LinkedList() {    //Initally LinkedList is empty, so first = null
        head = null;
    }

    /**
     * Other basic methods for LinkList
     */
    public boolean isEmpty() {
        return (head == null);
    }

    public void insertFirst(int data){
        Node newNode = new Node(data);

        newNode.next = head;    //newNode -> old head
        head = newNode;	        //head -> newNode
    }

    public void displayList(Node head){
        Node curr = head;	    //start iterating from head
        while(curr != null){
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }

    /**
     * Approach : Create 2 list pointers (before, after), one with elements less than value x.
     * Second with values greater than or equal to value x.
     * After we go through all the elements, merge the two lists
     */
    public Node partitionList(int partitionValue){
        //Current List head/first pointer
        Node curr = head;

        //New List pointers
        Node beforeStart = null;		//stores values less than partition
        Node beforeEnd = null;

        Node afterStart = null;			//stores values greater than partition
        Node afterEnd = null;

        //iterate through our original list
        while(curr != null) {
            Node newNode = new Node(curr.data);

            if(curr.data < partitionValue) {				    //add to  below List
                if(beforeStart == null) {
                    beforeStart = newNode;
                    beforeEnd = beforeStart;
                }else {
                    beforeEnd.next = newNode;
                    beforeEnd = newNode;
                }
            } else {											//add to above List
                if(afterStart == null){
                    afterStart = newNode;
                    afterEnd = afterStart;
                }else{
                    afterEnd.next = newNode;
                    afterEnd = newNode;
                }
            }
            curr = curr.next;
        }

        //Merge the lists
        if(beforeStart == null){
            beforeStart = afterStart;
        }
        beforeEnd.next = afterStart;	            //Point beforeEnd next to afterStart

        return beforeStart;
    }

}

public class Partition {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

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
        list.displayList(list.head);

        System.out.println("\nList after partition: ");
        Node newHead = list.partitionList(30);
        list.displayList(newHead);
    }
}
