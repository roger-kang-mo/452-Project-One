package pkg1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Parents extends Process implements Runnable{

	private String[] fileList;
	private ArrayList<Child> children = new ArrayList<Child>();
	private ByteArrayOutputStream out = new ByteArrayOutputStream();
	private int[] nums;

	public Parents(String[] order){

		fileList = order;
		
		makeChildren();
		String[] tempStrings = takeInputs();
		processInputs(tempStrings);
		Arrays.sort(nums);
		String temp = setupOutputData();

		try {
			out.write(temp.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void makeChildren(){
		for(int i = 0; i < fileList.length; i++){
			children.add(new Child(fileList[i]));
		}
	}

	private String[] takeInputs(){
		int i = 0;
		String[] tempStrings = new String[children.size()];

		for(Child c:children){
			tempStrings[i] = new String(c.getOutputStream().toByteArray());
			System.out.println(c.getFileName() + " has : " +(new String(c.getOutputStream().toByteArray())));
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

	private String setupOutputData(){
		String temp = "";
		for(int i = 0; i < nums.length; i++){
			temp += nums[i];
			if(i+1 != nums.length){
				temp+= ",";
			}
		}
		
		System.out.println("Parent temp);
		return temp;
	}

	public ByteArrayOutputStream getOutputStream() {
		return out;
	}

	@Override
	public InputStream getInputStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getErrorStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int waitFor() throws InterruptedException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int exitValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
