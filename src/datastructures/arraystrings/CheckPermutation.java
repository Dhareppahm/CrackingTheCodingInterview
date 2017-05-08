package datastructures.arraystrings;

import java.util.Arrays;
import java.util.Scanner;

/**
Problem 1.2 : Given two strings, write a method to decide if one is a permutation/anagrams of the other

Anagram => a word, phrase, or name formed by rearranging the letters of another, such as cinema, formed from iceman.
 */
public class CheckPermutation {

	/**
     * Approach 1: O(n)
	 * Check character counts, if each character count is same, then strings are anagrams
	 */
	private static boolean isAnagram_1(String str1, String str2){
		
		//IMP Note :Whenever we compare two strings, we know that if they are different lengths then they cannot be anagrams.
		if(str1.length() != str2.length()){
			return false;
		}
		
		//CHECK Count of characters in each of the strings		
		int[] letters = new int[256]; 	//assume charset is ASCII
		
		//Get letter count for str1
		for(char c : str1.toCharArray()){
			letters[c]++;	//increment the count. Note: Index is char ascii value, so same char will hold same index
		}		
		//Check counts for str2. Decrement the count. If count goes less than 0, then counts didn't match. so not anagram/permutation
		for(char c : str2.toCharArray()) {
			letters[c]--;
				if (letters[c] < 0){
				return false;
			}
		}		
			
		return true;
	}
	
	
	/**
	 * Method 2 (O(n log n)) : Since no. of character counts is same in both strings, just the seq is shuffled for anagrams, just
	 * sort both character arrays. Then compare them. They should be exactly same to be anagrams
	 */
	private static boolean isAnagram_2(String str1, String str2){
		
		//IMP Note :Whenever we compare two strings, we know that if they are different lengths then they cannot be anagrams.
		if(str1.length() != str2.length()){
			return false;
		}
				
		char[] c1 = str1.toCharArray();
		char[] c2 = str2.toCharArray();
		
		Arrays.sort(c1);
		Arrays.sort(c2);
		
		for(int i = 0; i < c1.length; i ++){
			if(c1[i] != c2[i]){
				return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter String 1: ");
		String str1 = s.nextLine();
		System.out.println("Enter String 2: ");
		String str2 = s.nextLine();

		boolean isAnagram1 = isAnagram_1(str1, str2);
		System.out.println("Is Anagram 1 : "+ isAnagram1);
		
		boolean isAnagram2 = isAnagram_2(str1, str2);
		System.out.println("Is Anagram 2 : "+ isAnagram2);
		
		s.close();
	}

}
