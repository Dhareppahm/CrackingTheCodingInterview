package datastructures.arraystrings;

/**
 1.4 Palindrome Permutation:
 Given a string, write a function to check if it is permutation of a palindrome.

 IMP LOGIC: To be a permutation of a palindrome, a string can have no more than 1 character that occurs odd times.
 */
public class PalindromePermutation {
    public static void main(String[] args) {
        PalindromePermutation obj = new PalindromePermutation();

        String s = "aabbcc   cd";
        boolean flag = obj.isPermutationPalindrome(s);
        System.out.println("String = "+ s+ "\nIs Permutation Palindrome : "+flag);
    }

    /**
     * Approach 1:
     * 1. Store character frequencies in table
     * 2. Loop through table array, and check no more than 1 characters has odd value.
     * Complexity: Best possible time and algorithms complexity is O(n)
     *
     * Improvement: Instead of constant array, we can also use a single bit (integer) to check if odd/even
     */
    private boolean isPermutationPalindrome(String s) {
        //Assumption - String will contain a-z characters, so defining array length accordingly
        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];

        //Store character frequencies in table
        for(Character ch : s.toCharArray()){
            if(ch != ' '){
                int x = Character.getNumericValue(ch) - Character.getNumericValue('a');
                table[x]++;
            }
        }

        //Loop through table array, and check no more than 1 characters has odd value.
        boolean foundOdd = false;
        for(int i = 0; i < table.length; i++){
            if(table[i] % 2 == 1){
                if(foundOdd){       //if its already true, then return false
                    return false;
                }
                foundOdd = true;    //set true, as atmost 1 character can be odd
            }
        }
        return true;
    }

}
