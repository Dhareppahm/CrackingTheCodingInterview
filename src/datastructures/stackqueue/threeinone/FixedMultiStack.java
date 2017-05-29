package datastructures.stackqueue.threeinone;

/**
 Problem 3.1: Three in One
 Describe how you could use single array to implement 3 stacks
 -------------------------------------------------------------------------------
 Approach 1: (Easy) Fixed Division

 Divide array in 3 fixed equal parts [0 - n/3), [n/3- 2n/3), [2n/3 - n)
 */

public class FixedMultiStack {
    private int numberOfStacks = 3;
    private int stackCapacity;          //capacity of each stack
    private int[] values;              //Stack array which actually contain elements
    private int[] topCounter;         //Hold top counter for each stack, just increment for push, decrement for pop.
                                       //to return top index, use corresponding offset.

    public FixedMultiStack(int stackCapacity) {
        this.stackCapacity = stackCapacity;
        values = new int[stackCapacity * numberOfStacks];
        topCounter = new int[numberOfStacks];                 //3 stacks so top array size is 3
    }

    /**
     * Stack operations: Push, Pop, Peek, IsEmpty
     */
    public void push(int data, int stackNum) throws Exception{

        if(isFull(stackNum)){
            throw new Exception("Stack Number "+ stackNum +" is Full. Cannot push to it.");
        }

        /* Increment top count & insert data at corresponding top index */
        topCounter[stackNum]++;                           //increment top count for corresponding stack
        int topIndex = getIndexOfTop(stackNum);     //get top index
        values[topIndex] = data;                    //insert at corresponding stack index
    }

    public int pop(int stackNum) throws Exception{

        if(isEmpty(stackNum)){
            throw new Exception("Stack Number " + stackNum + " is empty. Cannot pop from it.");
        }

        int topIndex = getIndexOfTop(stackNum);
        int val = values[topIndex];
        values[topIndex] = 0;       //clear value at top
        topCounter[stackNum]--;     //decrement top count

        return val;
    }

    public int peek(int stackNum) throws Exception {

        if(isEmpty(stackNum)){
            throw new Exception("Stack Number " + stackNum + " is empty. Cannot peek from it.");
        }

        int topIndex = getIndexOfTop(stackNum);
        int val = values[topIndex];

        return val;
    }

    private int getIndexOfTop(int stackNum){
        int offset = stackNum * stackCapacity;      //goto corresponding offset
        int topCount = topCounter[stackNum];                   //get incremented top count
        int index = offset + topCount - 1;

        return index;
    }

    private boolean isFull(int stackNum){
        return topCounter[stackNum] == stackCapacity ? true : false;      //full = top count equals stack capacity
    }

    private boolean isEmpty(int stackNum){
        return topCounter[stackNum] == 0 ? true : false;                //empty = 0 top count
    }


    //Driver main
    public static void main(String[] args) {
        FixedMultiStack stack = new FixedMultiStack(4);                 //each stack of size 4

        try {
            //Insert into 3rd stack
            stack.push(100, 1);
            stack.push(200, 1);
            stack.push(300, 1);
            stack.push(400, 1);

            //Insert into 2nd stack
            stack.push(1000, 2);
            stack.push(2000, 2);
            stack.push(3000, 2);
            stack.push(4000, 2);

            //Insert into 1st stack
            stack.push(10, 0);
            stack.push(20, 0);
            stack.push(30, 0);
            stack.push(40, 0);

            System.out.println("\nStack array:");
            //print stack array to check
            for(int i=0; i < stack.values.length; i++){
                System.out.print(stack.values[i] + " ");
            }

            //Now stack is full, push should give exception
//            stack.push(500, 1);

            stack.pop(0);
            stack.pop(1);
            stack.pop(2);

            System.out.println("\nStack array:");
            //print stack array to check
            for(int i=0; i < stack.values.length; i++){
                System.out.print(stack.values[i] + " ");
            }

            stack.push(500, 1);

            System.out.println("\nPeek from 1st stack = "+ stack.peek(0));
            System.out.println("\nPeek from 2nd stack = "+ stack.peek(1));
            System.out.println("\nPeek from 3rd stack = "+ stack.peek(2));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
