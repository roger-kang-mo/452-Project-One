package pkg1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Master {

	private String[] fileList;
	private OutputStream is1, is2;
	private Parents a1, b1;
	private ArrayList<Parents> parentList = new ArrayList<Parents>();
	private int[] nums;

	public Master(){
		System.out.println("Welcome!");

		//fileList = new String[new File(System.getProperty("user.home") +"/mok/CS452/project_one/").listFiles().length];
		if(!setupFiles()){
			System.out.println("There were no files found in the directory.  Exiting Program");
			System.exit(0);
		}
		System.out.println("There are currently " + fileList.length + " files in the directory");
		System.out.println("How many would you like to sort?\n:> ");

		int target = 0;

		boolean valid = false;
		do{
			Scanner s = new Scanner(System.in);
			try{
				target = s.nextInt();
				if(target <= fileList.length && target%2 == 0 && target >0){
					valid = true;
				}
			}catch(Exception e){

			}
			System.out.println("Invalid input.  Please enter a number between 4 and " + fileList.length +"\n:> ");

		}while(valid == false);

		createParents(target);
		String[] temp = takeInputs();
		processInputs(temp);

		Arrays.sort(nums);
		System.out.print("Final List is: ");
		for(int i =0; i< nums.length; i++)
			if(i+1 != nums.length)
				System.out.print(nums[i] + ", ");
			else
				System.out.print(nums[i] +"\n");
	}

	private boolean setupFiles(){
		boolean retValue = true;
		//fileList = (new File(System.getProperty("user.home") + "/mok/CS452/project_one/texts/").list());
		fileList = (new File(System.getProperty("user.home") + "/texts/").list());
		
		for(int i = 0; i < fileList.length; i++){
			if(fileList[i].equalsIgnoreCase("final.txt")){
				String[] tempFileList = new String[fileList.length-1];
				int counter = 0;
				
				for(int j = 0; j < fileList.length; j++){
					if(fileList[j].equalsIgnoreCase("final.txt")){
						;
					}
					else{
						tempFileList[counter] = fileList[j];
						counter++;
					}
				}
				fileList = tempFileList;
				break;
			}
		}

		if(fileList == null){
			retValue = false;
		}
		return retValue;
	}

	private void createParents(int target){
		String[] tempList = new String[target];
		for(int k = 0; k < target; k++){
			tempList[k] = fileList[k];
		}
		fileList = tempList;
		int size = target/2;
		
		String[] list1 = new String[size];
		String[] list2 = new String[size];

		for(int i = 0; i < size; i++){
			list1[i] = fileList[i];
		}
		for(int j = size; j < fileList.length; j++){
			list2[j-size] = fileList[j];
		}

		try {
			a1 = new Parents(list1);
			b1 = new Parents(list2);
			parentList.add(a1);
			parentList.add(b1);
		} catch (Exception e) {
			System.out.println("SOMETHING'S WRONG");
			e.printStackTrace();
		}
	}

	private String[] takeInputs(){
		int i = 0;
		String[] tempStrings = new String[parentList.size()];

		for(Parents p:parentList){
			tempStrings[i] = new String(p.getOutputStream().toByteArray());
			System.out.println("Parent has : " +(new String(p.getOutputStream().toByteArray())));
			i++;
		}
		return tempStrings;
	}

	private void processInputs(String[] tempStrings){
		int counter = 0;

		for(int i = 0; i < tempStrings.length; i++){
			counter+= tempStrings[i].split(",").length;
		}

		nums = new int[counter];

		counter = 0;
		for(int i = 0; i< tempStrings.length; i++){
			String[] data= tempStrings[i].split(",");
			for(int j = 0; j < data.length; j++){
				nums[counter] = Integer.parseInt(data[j]);
				counter++;
			}
		}
	}
	
	private void writeToFile(){
		String temp = "";
		try {
			BufferedOutputStream buff = new BufferedOutputStream(new FileOutputStream("final.txt"));
			for(int i = 0; i<nums.length;){
				temp+= nums[i] + "\n";
			}
			buff.write(temp.getBytes());
			
			buff.flush();
			buff.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		Master a = new Master();
	}


}
