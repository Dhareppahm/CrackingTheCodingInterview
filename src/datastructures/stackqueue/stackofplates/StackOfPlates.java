package datastructures.stackqueue.stackofplates;

import java.util.*;

/**
 Problem 3.3  Stack of Plates
 Imagine literal stack of plates. If stack gets too high, it might topple. Therefore in real life we would start a new stack
 when previous stack exceeds some threshold. Implement a data structure SetOfStacks that mimics this.

 SetOfStacks should be composed of several stacksList and should create a new stack once the previous one exceeds capacity.
 SetOfStacks.push() and SetOfStacks.pop() should behave identical to a single stack

 FOLLOW UP: Implement function popAt(int index) which performs pop operation on a specific sub-stack

 -----------------------------------------------------------------------------------------------------------------------
 Ask Interviewer if you can use java.util.Stack or have to implement own Stack class

 Below used java.util.Stack
 */
public class StackOfPlates {
    private int threshold;
    List<Stack<Integer>> stacksList = new ArrayList<>();

    public StackOfPlates(int threshold){
        this.threshold = threshold;
    }

    public void push(int data){
        //get current stack and push in it
        Stack<Integer> lastStack = getLastStack();
        if(lastStack == null || lastStack.size() == threshold){    //if last stack size equal to threshold, create a new stack
            Stack<Integer> newStack = new Stack<>();
            newStack.push(data);
            stacksList.add(newStack);
        }else{
            lastStack.push(data);
        }
    }

    public int pop(){
        //get current stack and pop from it
        Stack<Integer> stack = getLastStack();
        int value;
        if(stack.size() == 1){
            value = stack.pop();
            stacksList.remove(stack);
        }else{
            value = stack.pop();
        }
        return value;
    }

    public int popAt(int index){
        return leftShift(index, true);
    }

    private int leftShift(int index, boolean removeTop){
        Stack<Integer> stack = stacksList.get(index);

        int removedItem;
//        if(removeTop){
            removedItem = stack.pop();          //here we always remove top from that stack
//        }

        if(stack.isEmpty()){
            stacksList.remove(stack);
        }else if(stacksList.size() > index + 1){        //move top from next to the empty position in previous stack
            int v = leftShift(index + 1, true);
            stack.push(v);
        }
        return removedItem;

    }

    private boolean isEmpty(){
        Stack<Integer> lastStack = getLastStack();
        if(lastStack == null || lastStack.size() == 0)
            return true;
        return false;
    }

    private Stack getLastStack(){
        if(stacksList.size() == 0)
            return null;
        return stacksList.get(stacksList.size() - 1);
    }

    public static void main(String[] args) {
        StackOfPlates stackOfPlates = new StackOfPlates(5);     //say 5 items at max in stack

        stackOfPlates.push(1);stackOfPlates.push(2);stackOfPlates.push(3);stackOfPlates.push(4);stackOfPlates.push(5);
        stackOfPlates.push(6);stackOfPlates.push(7);stackOfPlates.push(8);stackOfPlates.push(9);stackOfPlates.push(10);
        stackOfPlates.push(11);stackOfPlates.push(12);

        //current stacks status
        System.out.println("Stack Status: ");
        for(int i=0; i < stackOfPlates.stacksList.size(); i++){
            Stack<Integer> stack = stackOfPlates.stacksList.get(i);
            System.out.println(Arrays.toString(stack.toArray()));
        }

        stackOfPlates.pop();stackOfPlates.pop();stackOfPlates.pop();

        System.out.println("Stack Status: ");
        for(int i=0; i < stackOfPlates.stacksList.size(); i++){
            Stack<Integer> stack = stackOfPlates.stacksList.get(i);
            System.out.println(Arrays.toString(stack.toArray()));
        }

        stackOfPlates.popAt(0);                 //replace top at index 0 stack = 5 with top of index 1 stack = 9
        System.out.println("Stack Status: ");
        for(int i=0; i < stackOfPlates.stacksList.size(); i++){
            Stack<Integer> stack = stackOfPlates.stacksList.get(i);
            System.out.println(Arrays.toString(stack.toArray()));
        }

    }

}
