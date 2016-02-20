package datastructures.arraystrings;

import java.util.Scanner;
/**
 * Problem 1.4
 * Write a method to replace all spaces in a string with '%20'.
 */
public class StringReplaceSpace {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		String inputString = s.nextLine();

		//1. using inbuilt function 
		String newInputString = inputString.trim().replaceAll("\\s", "%20");
		System.out.println(newInputString);


		//2. Using char array
		char[] chars = inputString.trim().toCharArray();
		StringBuilder sb = new StringBuilder();

		for(char c : chars){
			if(c == ' '){
				sb.append("%20");
			}else{
				sb.append(c);
			}
		}
		System.out.println(sb.toString());

		s.close();
	}

}
