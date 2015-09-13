package datastructures.arraystrings;

import java.util.Scanner;

/**
Implement an algorithm to determine if a string has all unique characters. What
if you cannot use additional data structures?
 * 
 */
public class CheckIfStringContainsUniqueCharacters {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		String inputString = s.nextLine();

		//complexity - O(n*2), n = length of string
		boolean isUnique_1 = isUniqueChar_1(inputString);

		System.out.println("Is Unique = " + isUnique_1);

		//Better solution : Complexity - O(n), n = length of string
		boolean isUnique_2 = isUniqueChar_2(inputString);

		System.out.println("Is Unique = " + isUnique_2);

		s.close();
	}

	
	/**
	 * Checks a char with every other character in string. So complexity is O(n*2)
	 */
	private static boolean isUniqueChar_1(String inputString) {
		
		boolean isUnique = true;

		for(int i = 0; i < inputString.length(); i++) {

			for(int j = i + 1; j < inputString.length(); j++) {				
				if(inputString.charAt(i) == inputString.charAt(j)) {
					isUnique = false;
					break;
				}
			}
		}
		return isUnique;
	}

	/**
	 * Create a boolean array, and store true at index location corresponding to character in string. 
	 * If character occurs again, the value would be true at the same index, and we will know char is repeated.
	 */
	private static boolean isUniqueChar_2(String inputString){

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
