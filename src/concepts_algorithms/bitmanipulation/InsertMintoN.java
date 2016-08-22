package concepts_algorithms.bitmanipulation;

/**
 Problem 5.1: Insertion

 N = 10000000000  M = 10011 i=2, j=6
 Output = 10001001100

 */
public class InsertMintoN {

    /**
     * Steps:
     * 1. N' = Clear bits j through i in N
     * 2. M' = Shift M so that it lines up with bits j through i
     * 3. Then N' OR M'
     */
    private int insertMintoN(int M, int N, int i, int j) {

        /**
         1. N' = Clear bits j through i in N
         Create a mask to clear bits i through j in N. Eg i=2, j =4 then mask should be 11100011
         Create left half of mask and right half of mask
         */
        int allOnes = ~0;
        int left = allOnes << (j+1);            //left = 11100000
        int right = (1 << i) - 1;              //right = 00000011

        int mask = left | right;
        int N_cleared = N & mask;

        //2. M' = Shift M so that it lines up with bits j through i
        int M_shifted = M << i;

        //3. M' OR N'
        return N_cleared | M_shifted;       //Done
    }

    public static void main(String[] args) {
        InsertMintoN obj = new InsertMintoN();

        //Check same test input and output
        int n = Integer.parseInt("10000000000",2);
        int m = Integer.parseInt("10011",2);
        int res = Integer.parseInt("10001001100",2);
        System.out.println("n="+n+" m="+m+" res="+res);
        System.out.println("-------------------------------");

        int N = 1024;
        int M = 19;

        int i=2, j = 6;

        System.out.println("M = "+ Integer.toBinaryString(M));
        System.out.println("N = "+ Integer.toBinaryString(N));

        int result = obj.insertMintoN(M, N, i, j);
        System.out.println("result = "+ Integer.toBinaryString(result));
        System.out.println(result);

    }

}
