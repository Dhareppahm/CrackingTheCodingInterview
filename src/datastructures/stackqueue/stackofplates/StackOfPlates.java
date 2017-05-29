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
public class StackOfPlates<T> {
    private int threshold;
    List<LinkedList<T>> stacksList = new ArrayList<>();

    public StackOfPlates(int threshold){
        this.threshold = threshold;
    }

    public void push(T data){
        //get current stack and push in it
        LinkedList<T> lastStack = getLastStack();
        if(lastStack == null || lastStack.size() == threshold){    //if last stack size equal to threshold, create a new stack
            LinkedList<T> newStack = new LinkedList<>();
            newStack.push(data);
            stacksList.add(newStack);
        }else{
            lastStack.push(data);
        }
    }

    public T pop(){
        //get current stack and pop from it
        LinkedList<T> stack = getLastStack();
        T value;
        if(stack.size() == 1){
            value = stack.pop();
            stacksList.remove(stack);
        }else{
            value = stack.pop();
        }
        return value;
    }

    public T popAt(int index){

        LinkedList<T> stack = stacksList.get(index);
        T removedItem = stack.pop();

        //Rollover
        rollover(index);

        return removedItem;
    }

    private void rollover(int index){

        //Remove last stack if empty
        if(index == stacksList.size() - 1){
            if(stacksList.get(index).isEmpty()){
                stacksList.remove(index);
            }
        }else{      //rollover
            T last = stacksList.get(index+1).removeLast();        //last element from next stack
            stacksList.get(index).push(last);                       //push it to current stack
            rollover(index+1);                                      //check next stack
        }
    }


    private LinkedList<T> getLastStack(){
        if(stacksList.size() == 0)
            return null;
        return stacksList.get(stacksList.size() - 1);
    }

    //print stack of plates
    private void printStackOfPlates(){
        for(int i=0; i < stacksList.size(); i++){
            LinkedList<T> stack = stacksList.get(i);
            System.out.println(Arrays.toString(stack.toArray()));
        }
    }

    public static void main(String[] args) {
        StackOfPlates sop = new StackOfPlates(5);     //say 5 items at max in stack

        sop.push(1);sop.push(2);sop.push(3);sop.push(4);sop.push(5);
        sop.push(6);sop.push(7);sop.push(8);sop.push(9);sop.push(10);
        sop.push(11);sop.push(12);

        //current stacks status
        System.out.println("Stack initial Status: ");
        sop.printStackOfPlates();

        sop.pop();sop.pop();sop.pop();

        System.out.println("Stack Status after 3 pop(): ");
        sop.printStackOfPlates();


        System.out.println("Stack Status after popAt(0): " + sop.popAt(0));
        sop.printStackOfPlates();

        sop.pop();
        System.out.println("Stack Status after 1 pop(): ");
        sop.printStackOfPlates();
    }
}
