package datastructures.stackqueue.sortstack;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 Problem 3.5  Sort Stack: Write a program to sort a stack such that the smallest items are on top. You can use an additional temporary stack,
 but you may not copy elements into any other data structure like array.

 The stack supports supports, push, pop, peek, isEmpty
 ------------------------------------------------------------------------------------------------------------------------
 Approach:
    PUSH (Sort logic):
    1. As smallest items should be on top, we take 2 stacks. 1st is main stack (which has smallest items on top) and
       2nd which is used as buffer.
    2. When a new item is to be pushed, we check it with existing top -
        If new item is smaller than existing top -> push new item in main stack
        Else pop existing tops and push into buffer stack, till new item is smaller than existing top.
    3. Then push new item and pop items from buffer into main stack.

 POP:  We simply pop and return the smallest from top of main stack
 PEEK: We simply return the smallest from top of main stack
 ISEMPTY: We check if main stack is Empty

 */
public class SortStack {
    Stack<Integer> mainStack = new Stack<>();
    Stack<Integer> bufferStack = new Stack<>();

    //Main sort logic
    public void push(int data){

        //Sort logic
        if(mainStack.isEmpty()){
            mainStack.push(data);
        }else if(data < mainStack.peek()){          //new data is smaller than current top
            mainStack.push(data);
        }else if(data > mainStack.peek()){          //new data is greater than current top
            while(!mainStack.isEmpty() && data > mainStack.peek()){
                bufferStack.push(mainStack.pop());  //pop from main and push into buffer
            }

            mainStack.push(data);                   //push new data into main stack

            while (!bufferStack.isEmpty()){         //copy from buffer back to main
                mainStack.push(bufferStack.pop());
            }
        }
    }

    public int pop(){
        if(mainStack.isEmpty()){
            throw new EmptyStackException();
        }
        return mainStack.pop();
    }

    public int peek(){
        if (mainStack.isEmpty()){
            throw new EmptyStackException();
        }
        return mainStack.peek();
    }

    public boolean isEmpty(){
        return mainStack.isEmpty();
    }

    //Driver main
    public static void main(String[] args) {
        SortStack stack = new SortStack();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(0);
        stack.push(4);

        System.out.println("Current stack: ");
        System.out.println(Arrays.toString(stack.mainStack.toArray()));

        System.out.println("Peek: "+ stack.peek());
    }
}
