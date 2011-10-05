package pkg1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Child {
	private int[] nums;

	public Child(String fileName, int pid){
		if(!readFile(fileName)){
			System.out.println("File was not found.");
		}
		Arrays.sort(nums);
	}

	/**
	 * Takes in the file name and attempts to read in the ints from the
	 * file into an int array.
	 *  
	 * @param fileName
	 * @return retVal: successful or not.
	 */
	private boolean readFile(String fileName){
		boolean retVal = false;
		ArrayList<Integer> temp = new ArrayList<Integer>();
		Scanner file = null;


		try{
			file = new Scanner(new File(fileName));
		}catch(FileNotFoundException e){
			retVal = false;
		}


		while(file.hasNextInt()){
			temp.add(file.nextInt());
		}

		// takes Integers from ArrayList and puts them into int array
		nums = new int[temp.size()];
		int j = 0;
		for(Integer i: temp){
			nums[j] = i.intValue();
			j++;
		}

		return retVal;
	}
	
	public static void main(String[] args){
		// Extracting pid
		String temp = args[1];
		int pid = Integer.parseInt(temp.substring(0, temp.indexOf('@')));
		
		Child a = new Child(args[0], pid);
		
	}
}
