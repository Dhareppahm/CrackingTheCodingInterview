package datastructures.arraystrings;

import java.util.Scanner;
/**
 * Problem 1.7
Given an image represented by an NxN matrix, where each pixel in the image is 4
bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
 *
 */
public class RotateMatrix {

	public static void main(String[] args) {
	
		Scanner s = new Scanner(System.in);
		System.out.println("=================== Matrix Rotation ====================");
		System.out.println("1. Rotate clockwise (+90)");
		System.out.println("2. Rotate anti-clockwise (-90)");
		System.out.println("3. Rotate clockwise (+180)");
		System.out.println("4. Rotate anti-clockwise (-180)");
		System.out.println("Choose an option : ");
		
		int option = s.nextInt();		
		
//		int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
		int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		/*String[][] matrix = {
				{"*","*","^","*","*"},
				{"*","*","|","*","*"},
				{"*","*","|","*","*"},
				{"*","*","|","*","*"},
				{"*","*","|","*","*"},
		};*/
		
		int length = matrix.length; 
				
		//print original matrix
		System.out.println("Original Matrix :");
		printMatrix(matrix, length);
		
		switch (option) {
		case 1:
			//Rotate the matrix in place : index by index - PLUS 90
			matrix = rotateMatrixby_plus90(matrix, length);		
			System.out.println("Rotated Matrix (+90) i.e clockwise");
			printMatrix(matrix, length);
			
			break;
			
		case 2:
			//Rotate the matrix in place : index by index - MINUS 90
			matrix = rotateMatrixby_minus90(matrix, length);		
			System.out.println("Rotated Matrix (-90) i.e anti-clockwise");
			printMatrix(matrix, length);
			
			break;
			
		case 3:
			//Rotate the matrix in place : index by index - PLUS 180
			matrix = rotateMatrixby_plus180(matrix, length);		
			System.out.println("Rotated Matrix (+180) i.e clockwise");
			printMatrix(matrix, length);
			
			break;

		case 4:
			//Rotate the matrix in place : index by index - MINUS 180
			matrix = rotateMatrixby_minus180(matrix, length);		
			System.out.println("Rotated Matrix (-90) i.e anti-clockwise");
			printMatrix(matrix, length);
			
			break;
			
		default:
			break;
		}	
				
		s.close();

	}
	
	private static void printMatrix(int[][] matrix, int length){
		for(int i=0; i < length; i++){
			for(int j=0; j < length; j++){
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	/**
O(n^2) time and O(1) space algorithm ( without any workarounds and hanky-panky stuff! )

Rotate by +90:
	1. Transpose
	2. Reverse each row
	
Rotate by -90:
	1. Transpose
	2. Reverse each column
	
Rotate by +180:
	Method 1: Rotate by +90 twice	
	Method 2: Reverse each row and then reverse each column

Rotate by -180:
	Method 1: Rotate by -90 twice	
	Method 2: Reverse each column and then reverse each row	
	Method 3: Reverse by +180 as they are same
	
	 */
	private static int[][] rotateMatrixby_plus90(int[][] matrix, int n){
		/** Here i and j are taken differently. Outer loop represents the layers, and we only need to go till half layers
	  	 ---> i (layer)
	  j	 1	2	3	4
	  |	 5	6	7	8
	  v	 9	10	11	12
	  	 13	14	15	16


		 Top  ----- Right
		 |            |
		 |            |
		 |            |
		 Left ----- Bottom
		 */
		//Layers
		for(int layer=0; layer < n/2; layer++) {
			//Elements
			int first = layer;
			int last = n - layer - 1;
			for(int i = first; i < last; i++){
				int offset = i - first;
				//Inorder to understand this, draw a [3][3] matrix and write the rotation. As per that write code below as you first did.

				//save Top
				int top = matrix[first][i];
				//Left -> Top
				matrix[first][i] = matrix[last - offset][first];
				//Bottom -> Left
				matrix[last - offset][first] = matrix[last][last - offset];
				//Right -> Bottom
				matrix[last][last - offset] = matrix[i][last];
				//Saved Top -> Right
				matrix[i][last] = top;
			}			
		}		
		return matrix;
	}
	
	
	
	private static int[][] rotateMatrixby_minus90(int[][]matrix, int n){
		/**
		 Top  ----- Right
		 |            |
		 |            |
		 |            |
		 Left ----- Bottom
		 */
		//Layers
		for(int layer = 0; layer < n/2 ; layer++){
			int first = layer;
			int last = n - 1 - first;
			//Each element
			for(int i = first; i < last; i++){
				int offset = i - first;

				//Save top
				int top = matrix[first][i];
				//Right -> Top
				matrix[first][i] = matrix[i][last];
				//Bottom -> Right
				matrix[i][last] = matrix [last][last - offset];
				//Left -> Bottom
				matrix [last][last - offset] = matrix[last - offset][first];
				//Saved Top -> Left
				matrix[last - offset][first] = top;
				
			}
		}	
		
		return matrix;
	}
	
	
	private static int[][] rotateMatrixby_plus180(int[][] matrix, int n){
		
		matrix = rotateMatrixby_plus90(matrix, n);
		matrix = rotateMatrixby_plus90(matrix, n);
		
		return matrix;
	}
	
	private static int[][] rotateMatrixby_minus180(int[][] matrix, int n){
		
		matrix = rotateMatrixby_minus90(matrix, n);
		matrix = rotateMatrixby_minus90(matrix, n);
		
		return matrix;
	}
	

}
