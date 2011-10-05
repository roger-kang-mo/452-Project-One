package pkg1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Parents {

	String fileName;
	String[] fileList;
	OutputStream in;

	public Parents(String order, int pid){
		
		int size = (new File(System.getProperty("user.home") +"/mok/CS452/project_one/").listFiles().length)/2;
		String[] list1 = (new File(System.getProperty("user.home") + "/mok/CS452/project_one/texts/").list());
		for(int i = 0; i < size; i++){
			fileList[i] = list1[i];
		}



	}
	
	private void makeChildren(){
		
		for(int i = 0; i < fileList.length; i++){
			try {
				in = Runtime.getRuntime().exec(new String[] {"java Child.java", fileList[i], ManagementFactory.getRuntimeMXBean().getName()}).getOutputStream();
			} catch (IOException e) {
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

}
