package pkg1;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Parents extends Process{

	private String fileName;
	private String[] fileList;
	private int mid;
	private ArrayList<Child> children = new ArrayList<Child>();
	private ByteArrayOutputStream out;
	private int[] nums;

	public Parents(String[] order, String id){
		
		fileList = order;

		mid = Integer.parseInt(id.substring(0,id.indexOf('@')));
		
		makeChildren();
		String[] tempStrings = takeInputs();
		processInputs(tempStrings);
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
			children.add(new Child(fileList[i], ManagementFactory.getRuntimeMXBean().getName()));
		}
	}
	
	private String[] takeInputs(){
		int i = 0;
		String[] tempStrings = new String[children.size()];
		
		for(Child c:children){
			tempStrings[i] = new String(c.getOutputStream().toByteArray());
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
		for(int i = 0; i< tempStrings.length; i++)
			for(int j = 0; j < tempStrings[i].length(); j++){
				String[] data= tempStrings[i].split(",");
				nums[counter] = Integer.parseInt(data[j]);
				counter++;
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
		return temp;
	}

	public OutputStream getOutputStream() {
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

}
