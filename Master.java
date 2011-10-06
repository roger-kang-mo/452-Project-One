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
				else{
					System.out.println("Invalid input.  Please enter a number between 4 and " + fileList.length +"\n:> ");
				}
			}catch(Exception e){

			}
			
			

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
		
		writeToFile();
		for(Parents p: parentList){
			p.destroy();
		}
		
		System.exit(0);
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
		int numParents = target/4;
		int parentCounter = 1;
		ArrayList<String[]> lists = new ArrayList<String[]>();
		
		String[] list1 = new String[size];
		String[] list2 = new String[size];
		
		int offSet = 0;
		
		for(int k = 0; k < numParents; k++){
			for(int i = offSet; i < size; i++){
				list1[i] = fileList[offSet];
				offSet++;
			}
			lists.add(list1);
		}
	
		try {
			for(String[] s:lists){
				parentList.add(new Parents(s, parentCounter));
				parentCounter++;
			}
			/**a1 = new Parents(list1);
			b1 = new Parents(list2);
			parentList.add(a1);
			parentList.add(b1);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String[] takeInputs(){
		int i = 0;
		String[] tempStrings = new String[parentList.size()];

		System.out.println("");
		for(Parents p:parentList){
			tempStrings[i] = new String(p.getOutputStream().toByteArray());
			System.out.println("Parent " + p.getParentNum() +" sent : " +(new String(p.getOutputStream().toByteArray())));
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
			String k = System.getProperty("user.home") + "/texts/";
			BufferedWriter buff = new BufferedWriter(new FileWriter(k + "final.txt"));
			int count = 0;
			for(int i = 0; i<nums.length;i++){
				if(i+1 < nums.length && count < 9)
					temp+= nums[i] + ", ";
				else
					temp+= nums[i];
				count++;
				if(count == 10){
					buff.write(temp);
					buff.newLine();
					temp = "";
					count = 0;
				}
			}
			
			
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
