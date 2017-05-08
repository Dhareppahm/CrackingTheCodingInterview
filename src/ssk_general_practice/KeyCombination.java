package ssk_general_practice;

import java.util.*;

/**
 IMP: http://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
 */
public class KeyCombination {

    public static void main(String[] args) {
        String[] arr = {"email","id","ssn","uscid"};
        List<String> resultList = new ArrayList<>();
        for(int i=0; i <= arr.length; i++) {
            printCombination(arr, arr.length, i, resultList);
        }
        System.out.println(Arrays.toString(resultList.toArray()));

        //Create %s list
        List<String> percentList = new LinkedList<>();
        for(String s : resultList){
            String[] temp = s.split("~");
            if(temp.length == 0){
                percentList.add("%s ");
            }else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < temp.length; i++) {
                    sb.append("%s ");
                }
                percentList.add(sb.toString());
            }
        }
        System.out.println(Arrays.toString(percentList.toArray()));
    }

    // The main function that prints all combinations of size r in arr[] of size n. This function mainly uses combinationUtil()
    static void printCombination(String arr[], int n, int r, List<String> resultList) {
        // A temporary array to store combinations
        String data[] = new String[r];

        // Print all combination using temprary array 'data[]'
        combinationUtil(arr, data, 0, n-1, 0, r, resultList);
    }

    static void combinationUtil(String arr[], String data[], int start, int end, int index, int r, List<String> resultList) {
        //Base case: Current combination is ready to be printed, print it
        StringBuilder sb = new StringBuilder();
        if (index == r) {
            for (int j=0; j < r; j++) {
                sb.append(data[j]);
                if(j+1 < r)
                    sb.append("~");
            }
            if(sb.length() > 0) {
                resultList.add(sb.toString());
            }
            return;
        }

        // replace index with all possible elements. The condition "end-i+1 >= r-index" makes sure that including one element
        // at index will make a combination with remaining elements at remaining positions
        for (int i=start; i<=end && end-i+1 >= r-index; i++) {
            data[index] = arr[i];
            combinationUtil(arr, data, i+1, end, index+1, r, resultList);
        }
    }

}
