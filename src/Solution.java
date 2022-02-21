
/**
 Problem 1.6
 Implement a method to perform basic string compression using the counts of repeated characters.
 For example, the string aabcccccaaa would become a2blc5a3.
 If the "compressed" string would not become smaller than the original string, your method should return the original string.
 */

public class Solution {

    public String compressString(String s){
        if(s.length()==0){
            return s;
        }
        StringBuilder sb = new StringBuilder();
        char prev = s.charAt(0);            //init with first char in string
        int count=0;

        for(int i=0; i < s.length(); i++){
            char curr = s.charAt(i);
            if(prev == curr){
                count++;
            }else{
                sb.append(prev);
                sb.append(count);
                prev=curr;
                count=1;
            }
            if(i==s.length()-1){
                sb.append(curr);
                sb.append(count);
            }
        }
        return sb.toString().length() < s.length() ? sb.toString() : s;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        String s = "aabcccccaaa";//"aabcccccaaa";
        System.out.println(obj.compressString(s));
    }
}
