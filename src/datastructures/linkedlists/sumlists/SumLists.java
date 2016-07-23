package datastructures.linkedlists.sumlists;

/**
 Problem: 2.5
 Sum lists: Add two numbers represented in linked list
 a. In reverse order
 b. In forward order

 eg: Reverse order (Units digit at head)
 First List: 7->1->6  // represents number 617
 Second List: 5->9->2 //  represents number 295
 Output
 Resultant list: 2->1->9  // represents number 912

 eg: Forward order (Units digit at head)
 First List: 6->1->7  // represents number 617
 Second List: 2->9->5 //  represents number 295
 Output
 Resultant list: 9->1->2  // represents number 912

 */

class Node {
    int data;
    Node next;
    public Node(int data) {
        this.data = data;
    }
}

class LinkedList{
    Node head;
    public LinkedList(){
        head = null;	//initialize as empty list
    }

    //Create LinkedList
    public void insertFirst(int data){
        Node node = new Node(data);
        node.next = head;
        head = node;
    }

    //Display LinkedList
    public void displayList() {
        Node current = head;
        while(current != null){
            System.out.print(current.data);
            if(current.next != null){
                System.out.print(" -> ");
            }
            current = current.next;
        }
    }

    //Reverse Lists utility used in Forward order
    public void reverse(){
        Node current = head;
        Node prev = null;
        Node next = null;

        while(current != null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    //Add two lists
    public Node addList(Node head1, Node head2){
        Node curr1 = head1;
        Node curr2 = head2;

        Node dummyHead = new Node(0);	//head of summation list
        Node curr = dummyHead;				//set curr = sum head

        int carry = 0;
        while(curr1 != null && curr2 != null) {
            int sum = curr1.data + curr2.data + carry;
            carry = sum / 10;
            sum = sum % 10;

            curr.next = new Node(sum);	//add num node to curr node

            curr = curr.next;	        //increment all pointers of list1, list2 and sum list
            curr1 = curr1.next;
            curr2 = curr2.next;
        }

        if(curr1 != null){			//if list1 has more numbers
            if(carry != 0) {
                curr.next = addList(curr1 , new Node(carry));
            }else{
                curr.next = curr1;
            }
        }else if(curr2 != null){	//if list2 has more numbers
            if(carry != 0) {
                curr.next = addList(curr2 , new Node(carry));
            }else{
                curr.next = curr2;
            }
        }else if(carry != 0){		//only carry is remaining
            curr.next = new Node(carry);
        }

        return dummyHead.next;
    }

}


public class SumLists {

    public static void main(String[] args) {

        /**
         * 1. Add two numbers represented in linked list
         */
        System.out.println("********* Add two numbers represented in linked list *********");
        //Create both the lists
        System.out.println("List 1: ");
        LinkedList list1 = new LinkedList();
        list1.insertFirst(6);
        list1.insertFirst(1);
        list1.insertFirst(7);
        list1.displayList();

        System.out.println("\nList 2: ");
        LinkedList list2 = new LinkedList();
        list2.insertFirst(2);
        list2.insertFirst(9);
        list2.insertFirst(5);
        list2.displayList();

        //Add both reversed lists with units digit at head (Reverse order)
        System.out.println("\nSum List: (Reverse Order)");

        LinkedList sumReverseList = new LinkedList();
        sumReverseList.head = list1.addList(list1.head, list2.head);
        sumReverseList.displayList();

        //For forward order, we can first reverse both lists so, units digit is at head and use same function
        System.out.println("\n\nForward order sum: ");

        //Create lists in forward order => 617 + 295 = 912
        list1.head = null;
        list1.insertFirst(7);
        list1.insertFirst(1);
        list1.insertFirst(6);

        list2.head = null;
        list2.insertFirst(5);
        list2.insertFirst(9);
        list2.insertFirst(2);

        //Reverse both the lists, as we want units digit at head
        System.out.println("\nReversed List 1: ");
        list1.reverse();
        list1.displayList();
        System.out.println("\nReversed List 2: ");
        list2.reverse();
        list2.displayList();

        //Add both reversed lists with units digit at head
        System.out.println("\nSum List: (Forward Order)");
        LinkedList sumForwardList = new LinkedList();
        sumForwardList.head = list1.addList(list1.head, list2.head);
        sumForwardList.displayList();
        System.out.println("\nFinal Sum: ");
        sumForwardList.reverse();
        sumForwardList.displayList();

    }

}
