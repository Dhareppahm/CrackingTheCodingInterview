package datastructures.linkedlists.palindrome;

import java.util.Stack;

/**
 Problem 2.6: Implement a function to check if list is palindrome

 -----------------------------------------------------------------------------------------------------------------------
 Approach 1: Reverse and Check
 Approach 2: Iterative
 Approach 3: Recursive
 */
class Node {
    int data;
    Node next;
    public Node(int data) {
        this.data = data;
    }
}

class LinkedList {
    Node head;

    public LinkedList() {
        head = null;    //initialize as empty list
    }

    public void displayList(Node head){
        Node curr = head;
        while(curr != null){
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }


    /**
     * Approach 1: Reverse and Compare
     * Create Reverse clone of main list. Compare data of both lists, if same then return true.
     */
    public boolean isPalindrome_ReverseCompare(Node head){

        Node cloneReverseListHead = reverseListAndClone(head);

        System.out.println("Reverse list: ");
        displayList(cloneReverseListHead);
        System.out.println("\nMain list: ");
        displayList(head);

        //iterate through both lists and compare data
        Node curr = head;
        Node reverse = cloneReverseListHead;

        while(curr != null && reverse != null){
            if(curr.data != reverse.data){
                return false;
            }
            curr = curr.next;
            reverse = reverse.next;
        }
        return true;
    }

    //Slight diff than regular reverse, as we have to create clone of reverse list. Not to do in-place reverse of main list
    private Node reverseListAndClone(Node head){
        Node curr = head;
        Node prev = null;

        while(curr != null){
            Node n = new Node(curr.data);        //Create Clone
            n.next = prev;
            prev = n;
            //curr = next;                          //difference
            curr = curr.next;
        }
        return prev;
    }


    /**
     * Approach 2: Iterative Approach
     * Keep two pointers: current and runner (jumps twice) as we don't know length of list. When runner reaches end,
     * current reaches middle. Put current node in Stack (i.e stack contains first half data)
     *
     * Then compare remaining half of list with elements in Stack. Return true if same.
     */
    public boolean isPalindrome_Iterative(Node head){

        //Keep two pointers: current and runner (jumps twice)
        Node curr = head;
        Node runner = head;
        Stack<Integer> stack = new Stack<>();

        while(runner != null && runner.next != null){
            stack.push(curr.data);                  //push 1st half into stack
            curr = curr.next;                       //move curr 1 step
            runner = runner.next.next;              //move runner 2 steps
        }

        //V.IMP: edge case - for Odd no. of elements, skip middle element
        if(runner != null){
            curr = curr.next;       //skip middle
        }

        //Now curr has reached middle
        while(curr != null){
            int top = stack.pop();
            if(curr.data != top){
                return false;
            }
            curr = curr.next;
        }

        return stack.isEmpty() ? true : false;
    }


    /**
     * Approach 3: Recursive
     */
    public boolean isPalindrome_Recursive(Node head){
        int length = getListLength(head);
        Result res = helper(head, length);

        return res.isMatch;
    }

    private Result helper(Node head, int length){
        //base case
        if(head == null || length == 0){        //even nodes
            return new Result(head, true);
        }else if(length == 1){                  //odd nodes
            return new Result(head.next, true);
        }

        Result result = helper(head.next, length-2);        //Recurse & reduce length by 2 each time

        //If not match, return false
        if(!result.isMatch || result.node == null){
            return result;
        }

        result.isMatch = head.data == result.node.data;     //Check and return if there is Match of data on other side
        result.node = result.node.next;                     //Return next node

        return result;
    }

    private int getListLength(Node head){
        Node curr = head;
        int length = 0;
        while(curr != null){
            length++;
            curr = curr.next;
        }
        return length;
    }
}

//Small utility class for recursive solution, as we need to return 2 values viz. boolean isMatch and next Node
class Result{
    boolean isMatch;
    Node node;

    public Result(Node node, boolean isMatch){
        this.isMatch = isMatch;
        this.node = node;
    }
}

public class CheckIfListPalindrome {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        list.head = new Node(1);
        list.head.next = new Node(2);
        list.head.next.next = new Node(3);
        list.head.next.next.next = new Node(2);
        list.head.next.next.next.next = new Node(1);

        System.out.println("Original list: ");
        list.displayList(list.head);

        System.out.println("\n### Approach 1: Reverse and Compare ");
        //Approach 1: Reverse and Compare
        boolean isPalindrome = list.isPalindrome_ReverseCompare(list.head);
        System.out.println("\nIs Palindrome (Reverse & compare) : "+ isPalindrome);


        //Approach 2: Iterative
        isPalindrome = list.isPalindrome_Iterative(list.head);
        System.out.println("\nIs Palindrome: (Iterative) : "+ isPalindrome);

        //Approach 3: Recursive
        isPalindrome = list.isPalindrome_Recursive(list.head);
        System.out.println("\nIs Palindrome: (Recursive) : "+ isPalindrome);

    }
}
