package datastructures.arraystrings;

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

		String compressedString1 = compressString(original);
		System.out.println("Final Output 1 : "+compressedString1);
	}
	
	/**
	 * Loop through each character in String from start, compare char at current index to char at next index
	 * if both chars are same, increment the count and proceed further
	 * else
	 * append the original char and count in string and then proceed further
	 * 
	 * Complexity : O(n) , n = length of string
	 */
	private static String getCompressedString(String s){
		int count = 1;
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < s.length(); i++) {

			if(i+1 < s.length() && s.charAt(i) == s.charAt(i+1)){
				count++;
			} else {
				sb.append(Character.toString(s.charAt(i)) + count);
				count = 1;	//reset count
			}
		}
		
		System.out.println("Compressed   : "+ sb.toString());
		
		//If the "compressed" string would not become smaller than the original string, your method should return the original string.
		return s.length() < sb.length() ? s : sb.toString();
	}

	/**
	 * Another solution using prev and curr char pointers
	 * Complexity: O(n)
	 */
	private static String compressString(String s){
		if(s.length()==0){					//return if empty string
			return s;
		}
		StringBuilder sb = new StringBuilder();
		char prev = s.charAt(0);            //init with first char in string
		int count=0;

		for(int i=0; i < s.length(); i++) {
			char curr = s.charAt(i);
			if(prev == curr){
				count++;
			}else{
				sb.append(prev);
				sb.append(count);
				prev=curr;
				count=1;				//reset count to 1 as new char found for first time
			}
			if(i==s.length()-1) {		//append last char (curr char) as prev was not set
				sb.append(curr);
				sb.append(count);
			}
		}
		return sb.toString().length() < s.length() ? sb.toString() : s;
	}

}
