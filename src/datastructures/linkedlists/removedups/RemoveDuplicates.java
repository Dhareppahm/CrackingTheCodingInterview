package datastructures.linkedlists.removedups;

import java.util.HashSet;
import java.util.Set;

/**
 Problem 2.1: Remove Dups
 Write code to remove duplicates from an unsorted linked list

 How would you solve this problem if a temporary buffer is not allowed?

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
    /**
     * General util methods
     */
    public void displayList(){
        Node curr = head;
        while(curr != null){
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public void insertFirst(int data){
        Node newNode = new Node(data);
        newNode.next = head;        //point new nodes next to present head
        head = newNode;             //move head to new node
    }

    /*****************************************************************************
     * Remove Duplicates : 2 approaches
     *****************************************************************************/
    //Approach 1 : Using a temporary buffer : O(n) but has space complexity
    public void removeDuplicatesUsingHashSet() {
        Node curr = head;
        Node prev = head;
        Set<Integer> set = new HashSet<>();

        while(curr != null) {

            //if hash set contains key, i.e a duplicate found so delete it
            if(set.contains(curr.data)){

                if(curr.next == null){          //delete duplicate element
                    prev.next = null;
                }else{
                    prev.next = curr.next;
                }
            }else{
                set.add(curr.data);     //add to hashSet and move forward
                prev = curr;	            //set previous to current only if not duplicate
            }
            curr = curr.next;	            //move current forward
        }
    }


    /**
     * Approach 2 : Here we use two pointers, current and runner to loop through the
     * list and remove duplicates. Current value is checked with Runner value.
     * Complexity is : O(n^2)
     */
    public void removeDuplicatesWithoutBuffer() {

        //Initialize the pointers: curr to head & runner starts from curr.
        //IMP: Remember we check runner.next so we don't need prev
        Node curr = head;
        while(curr != null){
            Node runner = curr;

            while(runner.next != null){
                if(curr.data == runner.next.data){           //duplicate => remove the runner's next node
                    runner.next = runner.next.next;          //point runners next to runners.next.next node
                }else{
                    runner = runner.next;                   //move runner forward
                }
            }
            curr = curr.next;                               //move curr forward
        }
    }



}

public class RemoveDuplicates {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.insertFirst(10);
        list.insertFirst(5);
        list.insertFirst(10);
        list.insertFirst(20);
        list.insertFirst(25);
        list.insertFirst(15);
        list.insertFirst(20);
        list.insertFirst(5);

        System.out.println("Original List: ");
        list.displayList();

        System.out.println("\nRemove Duplicates: Time: O(n) and Space: O(n)");
        list.removeDuplicatesUsingHashSet();
        list.displayList();

        System.out.println("\nRemove Duplicates: Time: O(n^2) and Space: O(1)");
        list.removeDuplicatesWithoutBuffer();
        list.displayList();
    }
}
