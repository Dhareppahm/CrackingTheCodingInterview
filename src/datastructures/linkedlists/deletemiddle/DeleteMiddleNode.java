package datastructures.linkedlists.deletemiddle;

/**
 Problem 2.3) Algorithm to delete a node in the middle of a singly linked list.

 IMP: You are given only access to that node, You don't have access to head of list
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
    /**
     * General util methods
     */
    public void displayList(Node n) {
        Node curr = n;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }

    /**
     * We don't have access to head of linkedlist in the method
     *
     * So logic here is to copy to next node to current node and delete next
     * a->b->c->d->e->f
     *
     * Delete c, replace c with d and point c's next to d's next
     * a->b->d->e->f
     * Don't care about previous nodes in list
     */
    public boolean deleteMiddleNode(Node deleteNode){
        if(deleteNode == null || deleteNode.next == null){
            return false;
        }
        Node nextNode = deleteNode.next;        //get next node

        deleteNode.data = nextNode.data;        //update data
        deleteNode.next = nextNode.next;        //point delete nodes next to next's next
        return true;
    }
}

public class DeleteMiddleNode {
    public static void main(String[] args) {
       LinkedList list = new LinkedList();
        list.head = new Node(1);
        list.head.next = new Node(2);
        Node deleteNode = new Node(3);
        list.head.next.next = deleteNode;
        list.head.next.next.next = new Node(4);
        list.head.next.next.next.next = new Node(5);
        list.head.next.next.next.next.next = new Node(6);

        System.out.println("Original list: ");
        list.displayList(list.head);

        System.out.println("\nAfter delete (not necessarily middle node): ");
        list.deleteMiddleNode(deleteNode);
        list.displayList(list.head);

    }
}
