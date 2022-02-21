package datastructures.arraystrings;

/**
 1.5 One Away
 There are 3 types of edits: Insert char, remove char, replace/update char
 Given two strings, write a function to check if they are 1-edit (0-edits) away.
 */
public class OneAway {
    public static void main(String[] args) {
        OneAway oa = new OneAway();
        String s1 = "pale";
        String s2 = "ale";
        boolean result = oa.isOneEditAway(s1, s2);
        System.out.println(result);
    }

    private boolean isOneEditAway(String s1, String s2) {

        if(s1.length() == s2.length()){                 //if equal length, then only replacement is possible
            return checkReplacement(s1, s2);
        }else if(s1.length() + 1 == s2.length()){       //if 1 diff in lengths, then insert/remove possible
            return checkInsertRemove(s1, s2);
        }else if(s1.length() == s2.length() + 1){
            return checkInsertRemove(s2, s1);
        }

        return false;
    }

    private boolean checkInsertRemove(String s1, String s2) {       //length of s1 < s2 as we check it above
        //i = pointer to shorter string s1, j = pointer to longer string s2
        boolean oneEdit = false;
        int i = 0, j = 0;
        while( i < s1.length() && j < s2.length()){
            if(s1.charAt(i) != s2.charAt(j)){
                if(oneEdit){
                    return false;
                }
                oneEdit = true;
                j++;
            }else{
                i++;
                j++;
            }
        }
        return true;
    }

    private boolean checkReplacement(String s1, String s2) {        //length of s1 & s2 are same
        boolean oneReplacement = false;
        for(int i=0; i < s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)){
                if(oneReplacement){                                 //if one replacement is already true, return false
                    return false;
                }
                oneReplacement = true;                              //else set it to true
            }
        }
        return true;
    }
}
