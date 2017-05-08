package datastructures.arraystrings;

import java.util.Scanner;

/**
Problem 1.1 : Is Unique
Implement an algorithm to determine if a string has all unique characters. What if you cannot use additional data structures?
 ----------------------------------------------------------------------------------------------------------------------------
 Approach:
 1. Create a boolean array, and store true at index location corresponding to character in string.
 2. If character occurs again, the value would be true at the same index, and we will know char is repeated.
 Time complexity: O(n), Space complexity : O(1)

 If we cannot use additional Space:
 1. Brute Force: O(n^2) - Check a character with all other characters in string
 2. Sort string and do linear search - O(n log n)

 */
public class IsUnique {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		System.out.println("Enter Input String: ");
		String inputString = s.nextLine();

		//complexity - O(n^2), n = length of string
		boolean isUnique_1 = isUniqueChar_BruteForce(inputString);

		System.out.println("Is Unique (Brute Force) = " + isUnique_1);

		//Better solution : Complexity - O(n), n = length of string
		boolean isUnique_2 = isUniqueChar(inputString);

		System.out.println("Is Unique (Optimal) = " + isUnique_2);

		s.close();
	}

	
	/**
	 * Brute Force: Checks a char with every other character in string. So complexity is O(n^2)
	 */
	private static boolean isUniqueChar_BruteForce(String inputString) {		
		for(int i = 0; i < inputString.length(); i++) {
			for(int j = i + 1; j < inputString.length(); j++) {				
				if(inputString.charAt(i) == inputString.charAt(j)) {    //if character match
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Create a boolean array, and store true at index location corresponding to character in string. 
	 * If character occurs again, the value would be true at the same index, and we will know char is repeated.
	 */
	private static boolean isUniqueChar(String inputString){
		boolean[] boolArray = new boolean[256];

		for(int i = 0; i < inputString.length(); i++) {
			int charValueIndex = inputString.charAt(i);

			//if true (i.e true value was set before at that index), so it means char value at that index is present in array
			if(boolArray[charValueIndex]){
				return false;				
			}
			//char not present at the index, so set value as true
			boolArray[charValueIndex] = true;
		}
		return true;
	}

}
