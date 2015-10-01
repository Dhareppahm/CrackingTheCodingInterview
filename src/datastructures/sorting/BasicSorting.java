package datastructures.sorting;

import java.util.Random;
import java.util.Scanner;

public class BasicSorting {

	//BUBBLE SORT : check Wiipedia
	private static int[] bubbleSort(int[] arr){
		boolean swapped = true;
		int j = 0;
		
		while(swapped){
			swapped = false;	//to exit after all swapped
			j++;
			
			for(int i = 0; i < arr.length - j; i++){
				if(arr[i] > arr[i+1]) {		//if left no is greater than right, swap it
					int temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;
					
					swapped = true;		//set to true, inorder to proceed checking
				}
			}
			System.out.println();
			display(arr);
		}
		
		return arr;
	}	
	
	//SELECTION SORT : check Wikipedia
	private static int[] selectionSort(int[] arr){
		
		for(int j = 0; j < arr.length - 1; j++){
			//init min to start. We start from left and keep the smallest no at left and proceed
			int min = j; 	
			
			for(int i = j+1 ; i < arr.length; i++) {
				if(arr[i] < arr[min]){
					min = i; 		//update min index to store new min index
				}				
			}
			
			//Swap min and out
			if(min != j){
				int temp = arr[j];
				arr[j] = arr[min];
				arr[min] = temp;
			}
		}
		
		return arr;
	}
	
	//INSERTION SORT : check Wikipedia
	private static int[] insertionSort(int[] arr){
		//start from second element
		
		for(int j = 1; j < arr.length; j++){		 // Start with 1 (not 0)
			int key = arr[j];	 			// the item to be inserted
			int i = j - 1;
			
			while((i >= 0) && (arr[i] > key)){	// Smaller values are moving up
				arr[i+1] = arr[i];
				i--;
			}
			
			arr[i + 1] = key;	 // Put the key in its proper location
		}			
		return arr;
	}
	
	//Display
	private static void display(int[] a) {
		for(int i=0; i < a.length; i++){
			System.out.print(a[i] + "\t");
		}
	}
	
	private static int getRandomNumber(){
		Random random = new Random();
		return random.nextInt(999);
	}
	
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Enter Array Size : ");
		int size = s.nextInt();
		int[] arr = new int[size];
		
		//Fill the array
		for(int i=0; i < size; i++) {
			arr[i] = getRandomNumber();//s.nextInt();
		}
		
		//Display the array
		display(arr);
		
		//1. Sort array using BUBBLE SORT
		int[] output1 = bubbleSort(arr.clone());	
		System.out.println("\nSort using BUBBLE SORT :");
		display(output1);
		
		//2. Sort array using SELECTION SORT
		int[] output2 = selectionSort(arr.clone());
		System.out.println("\nSort using SELECTION SORT :");
		display(output2);
		
		//3. Sort array using INSERTION SORT
		int[] output3 = insertionSort(arr.clone());
		System.out.println("\nSort using INSERTION SORT :");
		display(output3);
		
		s.close();
	}

}
