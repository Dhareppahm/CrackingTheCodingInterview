package concepts_algorithms.recursion_dp;

/**
 Magic Index: A magic index in an array A[ 1... n-1] is defined to be an index such that A[i] = i.
 Given a sorted array of distinct integers, write a method to find a magic index, if one exists, in array A.
 FOLLOW UP
 What if the values are not distinct?
 -----------------------------------------------------------------------------------------------------------------------
 Approach:
 A. For Sorted distinct values:
 Draw an example and see below conditions work:
    if(a[mid] == mid)
        return mid;
    if(a[mid] < mid)        //right half
        search mid+1 to end
    if(a[mid] > mid)        //left half
        search start to mid-1

 B. Follow up: Not Distinct values

 */
public class MagicIndex {
    public static void main(String[] args) {

    }
}
