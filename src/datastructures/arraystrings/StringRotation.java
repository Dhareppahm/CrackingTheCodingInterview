package datastructures.arraystrings;

/**
 Problem 1.9
 Assume you have a method isSubstring which checks if one word is substring of other. Given 2 string s1 and s2, write a
 code to check if s2 is rotation of s1 using one call to isSubstring method.
 Eg: s1 = "waterbottle" and s2 = "erbottlewat"
 */
public class StringRotation {

    public static void main(String[] args) {
        StringRotation sr = new StringRotation();

        String s1 = "waterbottle";
        String s2 = "erbottlewat";

        boolean flag = sr.isRotation(s1, s2);
        System.out.println(flag);
    }

    public boolean isRotation(String s1, String s2) {
        //First check the length of s1 and s2. They should be same else rotation is not correct
        if(s1.length() != s2.length()){
            return false;
        }

        String s1s1 = s1.concat(s1);
        return isSubstring(s1s1, s2);
    }

    private boolean isSubstring(String s1s1, String s2) {
        int j=0;
        for(int i=0; i < s1s1.length(); i++){
            if(s1s1.charAt(i) == s2.charAt(j)){
                j++;
            }
            if(j == s2.length()){
                return true;
            }
        }
        return false;
    }
}
