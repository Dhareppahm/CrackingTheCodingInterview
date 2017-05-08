package ssk_general_practice;

//[0,1,2,50,52,75]
//"3-49,51,53-74,76-99"

/*Approach:
Scan the array from left to right with 2 pointers : i = 0, j = i+1
Compare the diff. 
StringBuilder sb..append the solution
 */
public class FindGaps{

	private static int[] getInputArray(){
		int[] a = {0,1,2,50,52,75};
		return a;
	}

	public static void main(String[] args) {
		//Init the array
		int[] a = getInputArray();

		//output
		StringBuilder sb = new StringBuilder();
		int last = 0;
		
		for(int i=0, j=i+1; i < a.length && j < a.length; i++, j++){
			last = a[j];

			if(a[j]-a[i] > 1){
				if(a[i]+1 == a[j]-1){
					sb.append(a[i]+1 + ",");
				}else{
					sb.append((a[i]+1) + "-" + (a[j]-1) + ",");
				}
			}
		}
		//Handle the last gap
		if(last < 99){
			sb.append((last + 1) + "-" + 99);
		}
		//print final result
		System.out.println(sb.toString());

	}

}

//Brute-Force + Optimized
//Time Complexity of above code : O(n) : Linear
//Space complexity : StringBuilder sb and int last  : O(1) + O(1) = O(1)

