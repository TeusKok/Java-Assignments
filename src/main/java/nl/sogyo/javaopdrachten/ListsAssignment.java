import java.lang.Math;
import java.io.*;
import java.util.*;

public class ListsAssignment{
	public static void main(String []args){
	//long currentTimeMillis = System.currentTimeMillis();
		//int rnum = currentTimeMillis%10;
		int length = 10;
		int[] numberArray = new int[length];
		int[] sortedArray = new int[length];
		int heighest = 0;
		int lowest = 101;
		int secondLowest = 101;
		int number;
		boolean divisable;
		ArrayList<Integer> even = new ArrayList<Integer>();
		ArrayList<Integer> divThree = new ArrayList<Integer>();
		ArrayList<Integer> divFive = new ArrayList<Integer>();
		ArrayList<Integer> rest = new ArrayList<Integer>();
		for(int i = 0; i<length; i++){
			divisable=false;
			number =(int)(Math.random()*101);
			numberArray[i] = number;
			sortedArray[i] = number;
			//System.out.println(number);
			if(number>heighest)heighest = number;
			if(number<lowest){
				secondLowest = lowest;
				lowest = number;
			}
			else if(number<secondLowest) secondLowest = number;
			if(number%2==0) {even.add(number); divisable=true;}
			if(number%3==0) {divThree.add(number); divisable=true;}
			if(number%5==0) {divFive.add(number); divisable=true;}
			if(!divisable) rest.add(number);
		}
		
		
		System.out.println("The list of numbers is:\n"+Arrays.toString(numberArray));
		System.out.printf("\nThe heighest number is %d\n",heighest);
		System.out.printf("The sum of the two lowest numbers is %d\n",lowest+secondLowest);
		System.out.println("\nThe numbers that are divisible by 2 are:\n"+even);
		System.out.println("\nThe numbers that are divisible by 3 are:\n"+divThree);
		System.out.println("\nThe numbers that are divisible by 5 are:\n"+divFive);
		System.out.println("\nThe numbers that are not divisible by 2,3 or 5 are:\n"+rest);
		
		boolean sorted = false;
		
		int temp;
		int iteration =1;
		while(!sorted){
			sorted = true;
			for(int i = 0; i<length-iteration; i++){
				if(sortedArray[i]>sortedArray[i+1]){
					temp = sortedArray[i];
					sortedArray[i] = sortedArray[i+1];
					sortedArray[i+1] = temp;
					sorted = false;
				}
			}
			iteration++;
		}
		
		System.out.println("\nThe sorted list of numbers is:\n"+Arrays.toString(sortedArray));
	
	//System.out.println("current Timestamp: "+currentTimeMillis+ " milliseconds");
	

	
	}
}