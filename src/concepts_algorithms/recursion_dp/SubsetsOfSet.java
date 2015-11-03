package concepts_algorithms.recursion_dp;

import java.util.ArrayList;

public class SubsetsOfSet {

	//Using Recursion
	public static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set, int index){
		
		ArrayList<ArrayList<Integer>> allSubsets;
		
		//Base case
		if(set.size() == index){
			allSubsets = new ArrayList<ArrayList<Integer>>();
			allSubsets.add(new ArrayList<Integer>());	//empty set
		}else{
			allSubsets = getSubsets(set, index + 1);
			int item = set.get(index);
			
			ArrayList<ArrayList<Integer>> moreSubsets = new ArrayList<ArrayList<Integer>>();
			//Loop allSubsets and add item into newSubset and then put it into moreSubsets
			for(ArrayList<Integer> subset : allSubsets){
				ArrayList<Integer> newSubset = new ArrayList<Integer>();
				newSubset.addAll(subset);	//add all previous subsets
				newSubset.add(item);		//add item
				moreSubsets.add(newSubset);
			}
			
			//then put moreSubsets into final allSubsets
			allSubsets.addAll(moreSubsets);
		}
		
		return allSubsets;
	}
	
	public static void main(String[] args) {
		
		ArrayList<Integer> set = new ArrayList<Integer>();
		set.add(1);	set.add(2); set.add(3); 
		
		ArrayList<ArrayList<Integer>> allSubsets = getSubsets(set, 0);

		printAllSubsets(allSubsets);
	}

	private static void printAllSubsets(ArrayList<ArrayList<Integer>> allSubsets){
		for(ArrayList<Integer> subset : allSubsets){
			for(Integer i : subset){
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}
