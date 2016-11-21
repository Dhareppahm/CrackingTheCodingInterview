package datastructures.stackqueue.stackmin;

import java.util.Stack;

/**
Problem 3.2  How would you design a stack, in addition to push and pop, has a function MIN which returns minimum element.
 Push, Pop, Min should all operate in O(1) time
 */
public class StackMin extends Stack<Integer> {

    Stack<Integer> minStack = new Stack<>();        //separate stack to holds minimum values

    public void push(int data){
        if(data < min()){
            minStack.push(data);                    //if less than current min, also push to minStack
        }
        super.push(data);                           //super class is Stack
    }

    public Integer pop(){
        int value = super.pop();
        if(value == min()){                      //if popped value is same as current min, also pop from minStack
            minStack.pop();
        }
        return value;
    }

    public int min(){
        if(minStack.isEmpty()){
            return Integer.MAX_VALUE;       //error
        }else{
            return minStack.peek();             //return current min
        }
    }


    public static void main(String[] args) {
        StackMin stack = new StackMin();

        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(5);
        stack.push(5);
        stack.push(2);
        stack.push(3);

        System.out.println("Current Min = "+ stack.min());
        stack.pop();
        System.out.println("Current Min = "+ stack.min());
        stack.pop();
        System.out.println("Current Min = "+ stack.min());

    }
}
