package ssk_advanced_algorithms.maximum_subarray;

/**
 Kadane Algorithm: Find Maximum subarray

 Find the sum of contiguous subarray within a one-dimensional array of numbers which has the largest sum.
 For Eg, for the sequence of values −2, 1, −3, 4, −1, 2, 1, −5, 4;
 the contiguous subarray with the largest sum is 4, −1, 2, 1, with sum 6.
 -----------------------------------------------------------------------------------------------------------------------
Kadane Algorithm:

 Initialize:
    max_ending_here = a[0]
    max_so_far = a[0]

 Loop for each element of the array
 (a) max_ending_here = MAX(a[i], max_ending_here + a[i])
 (b) max_so_far = MAX(max_so_far, max_ending_here)
 return max_so_far
 */
public class KadaneAlgorithm {

    public static void main(String[] args) {
        KadaneAlgorithm obj = new KadaneAlgorithm();
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int maxSum = obj.findMaxSum(arr);
        System.out.println(maxSum);
    }

    private int findMaxSum(int[] arr){
        int maxEndingHere = arr[0];
        int maxSoFar = arr[0];

        for(int i=1; i < arr.length; i++) {
            maxEndingHere = Math.max(arr[i],maxEndingHere + arr[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }


}
