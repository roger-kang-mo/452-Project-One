package pkg1;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Parents extends Process{

	String fileName;
	String[] fileList;
	DataOutputStream in;
	int mid;

	public Parents(String[] order, String id){
		
		fileList = order;
		in = new DataOutputStream(this.getOutputStream());

		mid = Integer.parseInt(id.substring(0,id.indexOf('@')));
	}
	
	private void makeChildren(){
		
		for(int i = 0; i < fileList.length; i++){
			try {
				//Runtime.getRuntime().exec(new String[] {"java Child.java", fileList[i], ManagementFactory.getRuntimeMXBean().getName()}).getOutputStream();
			} catch (Exception e) { 
				System.out.println("SOMETHING'S WRONG");
				e.printStackTrace();
			}
			
		}
	}


	public static void main(String[] args){
		
		// Extracting pid
		String temp = args[1];
		int pid = Integer.parseInt(temp.substring(0, temp.indexOf('@')));
		
		System.out.print("parent created");
		
		Parents a = new Parents(args[0], pid);

	}

	public OutputStream getOutputStream() {
		return null;
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
