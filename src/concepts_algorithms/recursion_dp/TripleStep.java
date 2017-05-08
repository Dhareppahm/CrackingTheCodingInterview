package concepts_algorithms.recursion_dp;

import java.util.Arrays;

/**
 Triple Step: A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3 steps at a time.
 Implement a method to count how many possible ways the child can run up the stairs.
 ---------------------------------------------------------------------------------------------------------------------
 Complexity:
    With memoization: O(n)
    Without memoization: O(3^n) as the recursion tree branches in 3 branches at each step
 */
public class TripleStep {

    public int countWays(int n){

        int[] memo = new int[n+1];
        Arrays.fill(memo, -1);
        helper(n, memo);
        System.out.println(Arrays.toString(memo));
        return memo[n];
    }

    public int helper(int n, int[] memo){

        if(n < 0) {                 //Base case
            return 0;
        }else if(n == 0) {          //Base case
            return 1;
        }else if(memo[n] > -1){     //return already computed result
            return memo[n];
        }else{                      //calculate
            memo[n] = helper(n-1, memo) + helper(n-2, memo) + helper(n-3, memo);
            return memo[n];
        }

    }

    public static void main(String[] args) {
        TripleStep ts = new TripleStep();
        int noOfSteps = 10;

        int ways = ts.countWays(noOfSteps);
        System.out.println("No of ways : " + ways);
    }
}
