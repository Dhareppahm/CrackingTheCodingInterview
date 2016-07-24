package datastructures.linkedlists.intersection;

/**
 Problem 2.7 : Intersection
 Given 2 singly linkedlists, determine if the two lists intersect. Return the intersecting node. Node that the intersection
 is defined based on reference and not value.
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

    public void display(Node head){
        Node curr = head;
        while(curr != null){
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }

    /**
     Approach:
        1. Find length and tail nodes of both lists.
        2. If tail nodes different, no intersection
        3. Set 2 pointers to start of both list. Advance longer pointer to difference in lengths
        4. Move both pointers until there is a intersection found
     */
    public Node findIntersection(Node head1, Node head2){

        Result result1 = getLengthAndTail(head1);
        Result result2 = getLengthAndTail(head2);

        if(result1.tail != result2.tail){       //no intersection, if tail nodes different
            return null;
        }

        //Set 2 pointers to start of both list. Advance longer pointer to difference in lengths
        Node longer = result1.length > result2.length ? head1 : head2;
        Node shorter = result1.length > result2.length ? head2 : head1;

        //Move longer pointer ahead as difference in lengths
        int k = Math.abs(result1.length - result2.length);
        longer = moveKNodesAhead(longer, k);

        //Move both pointers until there is a intersection found
        while(longer != shorter){
            longer = longer.next;
            shorter = shorter.next;
        }

        //Return either one
        return longer;
    }

    private Result getLengthAndTail(Node head) {
        Node curr = head;
        int length = 0;
        while(curr != null){
            length++;
            curr = curr.next;
        }
        return new Result(length, curr);
    }

    private Node moveKNodesAhead(Node head, int k){
        Node curr = head;
        while(k > 0 && curr != null){
            curr = curr.next;
            k--;
        }
        return curr;
    }


}

//Utility class to return both, length and tail nodes
class Result{
    int length;
    Node tail;

    public Result(int length, Node tail){
        this.length = length;
        this.tail = tail;
    }
}

public class CheckIfListsIntersect {

    public static void main(String[] args) {
        LinkedList list1 = new LinkedList();
        LinkedList list2 = new LinkedList();

        /*
         1 -> 2 -> 3 -> 4
                         \
                          5 -> 6 -> 7 -> 8
                        /
                10 -> 20

         Should return Node 5
         */
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n10 = new Node(10);
        Node n20 = new Node(20);

        list1.head = n1;
        list1.head.next = n2;
        list1.head.next.next = n3;
        list1.head.next.next.next = n4;
        list1.head.next.next.next.next = n5;
        list1.head.next.next.next.next.next = n6;
        list1.head.next.next.next.next.next.next = n7;
        list1.head.next.next.next.next.next.next.next = n8;

        list2.head = n10;
        list2.head.next = n20;
        list2.head.next.next = n5;
        list2.head.next.next.next = n6;
        list2.head.next.next.next.next = n7;
        list2.head.next.next.next.next.next = n8;

        System.out.println("List 1:");
        list1.display(list1.head);

        System.out.println("\nList 2:");
        list2.display(list2.head);

        Node intersectionNode = list1.findIntersection(list1.head, list2.head);
        System.out.println("\nInterection Node = "+ intersectionNode.data);
    }
}
