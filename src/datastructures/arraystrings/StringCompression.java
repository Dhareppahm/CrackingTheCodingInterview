package datastructures.arraystrings;

import java.util.Scanner;

/**
 Problem 1.6
 Implement a method to perform basic string compression using the counts of repeated characters.
 For example, the string aabcccccaaa would become a2blc5a3.
 If the "compressed" string would not become smaller than the original string, your method should return the original string.
 */
public class StringCompression {

	public static void main(String[] args) {
		String original = "aabcccccaaa";
        System.out.println("Original string : "+ original);

        String compressedString = getCompressedString(original);
		System.out.println("Final Output : "+compressedString);
	}
	
	/**
	 * Loop through each character in String from start, compare char at current index to char at next index
	 * if both chars are same, increment the count.
	 * else
	 * append the original char and count in string
	 * 
	 * Complexity : O(n) , n = length of string
	 */
	private static String getCompressedString(String original){
		int count = 1;
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i <= original.length() - 1; i++) {
			
			char currentChar = original.charAt(i);
			char nextChar;

			if(i != original.length() - 1){
				nextChar = original.charAt(i + 1);
			} else {
				nextChar = '\n';	//any separator which will not be in string to break loop and print final count
			}
			
			if(currentChar == nextChar){
				count++;
			} else {
				sb.append("" + currentChar + count);
				count = 1;	//reset count
			}
		}
		
		System.out.println("Compressed   : "+ sb.toString());
		
		//If the "compressed" string would not become smaller than the original string, your method should return the original string.
		if(original.length() < sb.toString().length()){
			return original;
		}else{
			return sb.toString();
		}
	}

}
