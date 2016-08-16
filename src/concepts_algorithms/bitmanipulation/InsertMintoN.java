package concepts_algorithms.bitmanipulation;

/**
 Problem 5.1: Insertion
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

        int M = 9;
        int N = 2;

        int i=1, j = 2;

        System.out.println("M = "+ Integer.toBinaryString(M));
        System.out.println("N = "+ Integer.toBinaryString(N));

        int result = obj.insertMintoN(M, N, i, j);
        System.out.println(result);

    }

}
