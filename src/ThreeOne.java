import java.util.*;
public class ThreeOne {
	public static void main(String[] args){
		int[] testCase1 = new int[]{2, 1, 5, 3};
		int[] testCase2 = new int[]{1, 2, 5, 3};
		int[] testCase3 = new int[]{2, 4, 3, 6};
		int[] testCase4 = new int[]{0, 0, 0, 0};
		int[] testCase5 = new int[]{100, 0, 0, 0, 0};
		int[] testCase6 = new int[]{100, 10000, 100, 200, 100};
		int[] testCase7 = new int[]{2, 5, 5, 3};
		int[] testCase8 = new int[]{1, 1, 5, 3};
		int[] testCase9 = new int[]{1, 1, 1, 1};
		
		System.out.println("The result for [2, 1, 5, 3] " + "is " + findMax(testCase1));
		System.out.println("The result for [1, 2, 5, 3] " + "is " + findMax(testCase2));
		System.out.println("The result for [2, 4, 3, 6] " + "is " + findMax(testCase3));
		System.out.println("The result for [0, 0 ,0, 0] " + "is " + findMax(testCase4));
		System.out.println("The result for [100, 0, 0, 0 ,0] " + "is " + findMax(testCase5));
		System.out.println("The result for [100, 1000, 100 ,200, 100] " + "is " + findMax(testCase6));
		System.out.println("The result for [2, 5, 5, 3] " + "is " + findMax(testCase7));
		System.out.println("The result for [1, 1, 5, 3] " + "is " + findMax(testCase8));
		System.out.println("The result for [1, 1, 1, 1] " + "is " + findMax(testCase9));
	}
	
	public static int findMax(int[] pots){
		int len = pots.length;
		
		int maxIndex = 0;
		int maxValue = 0;
		int maxCoins = 0;
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> table = new ArrayList<Integer>();
		/*
		 * 1) Copy array into a list
		 * 2) Initialize Memoization Table of size of the list
		 * 3) Iterate through the list, and keep track of the minimum integer that gives maximum value
		 * 4) Append the maximum value into the memoization table
		 * 5) Remove the integer from the list
		 * 6) Repeat 2 - 5 until the list is empty
		 */
		
		//Base case 1: Add the biggest number possible in the beginning of the list
		list.add(Integer.MAX_VALUE);
		
		for(int i = 0; i < len; i++){
			list.add(pots[i]);
		}
		int value = 0;
		int maxCoinValue = 0;
		int size = list.size();
		
		
		//Base case 2: Add 0 to the beginning of the memoization table
		table.add(0);
		
		while(!list.isEmpty()){
			//Base case 3: if the list is of size 2, just add that value
			if(size == 2){
				table.add(list.get(1));
				break;
			}			
			
			//This for loop constitutes the subproblem: finding the pot with the minimum number of stones that gives maximum value of coins when chosen
			for(int j = 1; j < size; j++){
				
				//If it is on the front edge, just add the first two values
				if(j == 1){
					value = list.get(j) + list.get(j + 1);
				}
				
				//If it is on the end edge, just add the last two values
				else if(j == size - 1){
					value = list.get(j - 1) + list.get(j);
				}
				
				//Otherwise add previous, current, and next pots' stones
				else{
					value = list.get(j - 1) + list.get(j) + list.get(j + 1);
				}
				
				//If max net number of stones is greater than the current max, replace it
				if(value - list.get(j) > maxValue){
					maxValue = value - list.get(j);
					maxCoinValue = value;
					maxIndex = j;
				}
				
				//if the max net number of stones is the same, pick the pot with less number of stones so that we lose less stones
				else if(value - list.get(j) == maxValue && list.get(j) <= list.get(maxIndex)){
					maxIndex = j;
				}
			}
			
			//Remove the pot that gives us the max value of stones, and add the value to the memoization list
			list.remove(maxIndex);
			table.add(maxCoinValue);
			
			//Reset the variables accordingly
			size = list.size();
			maxValue = 0;
			maxIndex = 0;
		}
		
		for(int index = 0; index < table.size(); index++){
			maxCoins += table.get(index);
		}
		
		return maxCoins;
	}
}
