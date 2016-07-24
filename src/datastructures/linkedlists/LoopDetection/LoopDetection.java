package datastructures.linkedlists.LoopDetection;

/**
 Problem 2.8: Loop detection: Given a circular linked list (corrupt) implement an alogrithm that returns node at beginning of loop
 */
class Node{
    int data;
    Node next;

    public Node(int data){
        this.data = data;
    }
}

class LinkedList{
    Node head;

    public LinkedList(){
        head = null;
    }

    public void displayList(){
        Node curr = head;
        while(curr != null){
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }

    /**
     * 1. Two pointers: slowRunner - moves 1 step, fastRunner - moves 2 steps
     * 2. Since there is a loop, both will meet at "Collision Point"
     * 3. When they collide at "Collision Point", move one pointer to head of list
     * 4. Now, move both pointers at same speed. They will meet at beginning of loop. Return it.
     */
    public Node findBeginningOfLoop(Node head){
        Node slow = head;
        Node fast = head;

        //Till both pointers meet
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {          //Collision
                break;
            }
        }

        //Error check - no meeting point
        if(fast == null || fast.next == null){
            return null;
        }

        //After they collide, move 1 pointer to head of list
        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }

        //return either pointers
        return slow;
    }

}

public class LoopDetection {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        /*
         1 -> 2 -> 3 \
                9     4
               /       \
              8         5
              \        /
                7 -  6

         Should return Node 3
         */
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);

        list.head = n1;
        list.head.next = n2;
        list.head.next.next = n3;
        list.head.next.next.next = n4;
        list.head.next.next.next.next = n5;
        list.head.next.next.next.next.next = n6;
        list.head.next.next.next.next.next.next = n7;
        list.head.next.next.next.next.next.next.next = n8;
        list.head.next.next.next.next.next.next.next.next = n9;
        list.head.next.next.next.next.next.next.next.next.next = n3;

//        list.displayList();       //will go into infinite loop

        Node beginLoopAt = list.findBeginningOfLoop(list.head);
        System.out.println("Loop begins at : "+ beginLoopAt.data);
    }
}
