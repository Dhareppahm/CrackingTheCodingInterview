package datastructures.arraystrings;

import java.util.Arrays;

/**
 Problem 1.8
 Zero Matrix: Write an algorithm such that if an element in MxN matrix is 0, its entire row and column are set to zero
 */
public class ZeroMatrix {
    public static void main(String[] args) {
        ZeroMatrix zm = new ZeroMatrix();

        int[][] matrix = {
                            {0,2,8,0},
                            {0,3,5,0},
                            {1,8,9,2},
                            {7,5,9,4},
                            {9,3,7,8}
                        };

        System.out.println("Original Matrix: ");
        zm.displayMatrix(matrix);

        zm.createZeroMatrix(matrix);

        System.out.println("Zero Matrix: ");
        zm.displayMatrix(matrix);
    }

    public void createZeroMatrix(int[][] matrix){
        //If we don't store 0 occuring rows, columns we will end up creating entire matrix full of 0's
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[] zeroRows = new boolean[rows];
        boolean[] zeroCols = new boolean[cols];

        //Traverse through the matrix and record the rows and columns where 0 is present
        for(int i=0; i < rows; i++){
            for(int j=0; j < cols; j++){
                if(matrix[i][j] == 0){
                    zeroRows[i] = true;
                    zeroCols[j] = true;
                }
            }
        }

        System.out.println("Zero rows: " + Arrays.toString(zeroRows));
        System.out.println("Zero cols: " + Arrays.toString(zeroCols));

        //Make corresponding rows 0
        for(int i=0; i < zeroRows.length; i++){
            if(zeroRows[i] == true){
                for(int j=0; j < cols; j++)
                    matrix[i][j] = 0;
            }
        }

        //Make corresponding cols 0
        for(int i=0; i < zeroCols.length; i++){
            if(zeroCols[i] == true){
                for(int j=0; j < rows; j++)
                    matrix[j][i] = 0;
            }
        }

    }


    private void displayMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for(int i=0; i < rows; i++){
            for(int j=0; j < cols; j++){
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
