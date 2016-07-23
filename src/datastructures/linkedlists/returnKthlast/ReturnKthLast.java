package datastructures.linkedlists.returnKthlast;

/**
 Problem 2.2) Implement an algorithm to find the kth to last element of a singly linked list.
 */
class Node{
    int data;
    Node next;
    public Node(int data){
        this.data = data;
    }
}

class LinkedList {
    Node head;
    public LinkedList() {
        head = null;
    }
    /**
     * General util methods
     */
    public void displayList() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }
    public boolean isEmpty() {
        return (head == null);
    }
    public void insertFirst(int data) {
        Node newNode = new Node(data);
        newNode.next = head;        //point new nodes next to present head
        head = newNode;             //move head to new node
    }

    /**
     * Approach : Create 2 pointers, Current and Runner and start running them with a difference of K.
     * Current will start from first and Runner will start K nodes ahead of Current Increment both pointers.
     * When Runner will reach end of List, Current should point to Node we require.
     */
    public Node findKthLastElement(int k){
        Node curr = head;
        Node runner = head;

        //Move runner K nodes ahead
        for(int i=0; i < k; i++) {
            if(runner == null) {
                return null;                //if out of bounds
            }
            runner = runner.next;
        }

        //Loop till runner reaches end and move both pointers together
        while(runner != null){
            runner = runner.next;
            curr = curr.next;
        }
        //When runner reaches end of list, the current pointer should point to Kth to last link
        System.out.println(k + " th last element is :" + curr.data);
        return curr;
    }


}
public class ReturnKthLast {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.insertFirst(40);
        list.insertFirst(35);
        list.insertFirst(30);
        list.insertFirst(25);
        list.insertFirst(20);
        list.insertFirst(15);
        list.insertFirst(10);
        list.insertFirst(5);

        System.out.println("Original List: ");
        list.displayList();

        System.out.println();
        int k = 4;
        list.findKthLastElement(k);
    }
}
