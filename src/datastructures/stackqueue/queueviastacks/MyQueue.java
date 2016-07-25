package datastructures.stackqueue.queueviastacks;

import java.util.Stack;

/**
 Problem 2.4 Implement MyQueue class which implements a queue using two Stacks
 -------------------------------------------------------------------------------------------------------

 */
public class MyQueue<T> {
    Stack<T> stackNewest;
    Stack<T> stackOldest;

    public MyQueue(){
        stackNewest = new Stack<>();
        stackOldest = new Stack<>();
    }

    /**
     * Queue operations - add(), dequeue/remove(), peek(), isEmpty()
    */

    /**
     * Approach:
     * Keep adding items into new stack, from add operation
     * When dequeue is called -
     *      If old stack is Empty => pop all items from next stack and push in old stack.
     *      If old stack not empty => pop from old stack and return     *
     */
    public void add(T data){
        stackNewest.push(data);
    }

    public T dequeue(){
        shiftStacks();
        return stackOldest.pop();
    }

    public T peek(){
        shiftStacks();
        return stackOldest.peek();
    }

    private void shiftStacks(){
        if(stackOldest.isEmpty()){
            while(!stackNewest.isEmpty()){
                stackOldest.push(stackNewest.pop());
            }
        }
    }

    public boolean isEmpty(){
        return stackNewest.isEmpty();
    }

    public int size(){
        return stackNewest.size() + stackOldest.size();
    }


    //Driver function
    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        queue.add(6);

        System.out.println("Queue size = "+queue.size());

        System.out.println("Dequeue: "+ queue.dequeue());
        System.out.println("Dequeue: "+ queue.dequeue());
        System.out.println("Peek: "+ queue.peek());
        System.out.println("Queue size = "+queue.size());
    }
}
