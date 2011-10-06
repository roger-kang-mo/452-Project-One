package pkg1;

import java.io.File;
import java.io.*;
import java.lang.management.ManagementFactory;

public class Master {

	private String[] fileList;
	OutputStream is1, is2;
	Parents a1, b1;

	public Master(){	
		System.out.println(System.getProperty("user.home"));
		//fileList = new String[new File(System.getProperty("user.home") +"/mok/CS452/project_one/").listFiles().length];
		if(!setupFiles()){
			System.out.println("There were no files found in the directory.  Exiting Program");
			System.exit(0);
		}
		split();
	}

	private boolean setupFiles(){
		boolean retValue = true;
		//fileList = (new File(System.getProperty("user.home") + "/mok/CS452/project_one/texts/").list());
		fileList = (new File(System.getProperty("user.home") + "/texts/").list());
		System.out.println(fileList.length + " ");
		if(fileList == null){
			retValue = false;
		}
		return retValue;
	}

	private void split(){
		//int size = (new File(System.getProperty("user.home") +"/mok/CS452/project_one/").listFiles().length)/2;
		int size = (new File(System.getProperty("user.home") +"/texts/").listFiles().length)/2;
		String[] list1 = new String[size];
		String[] list2 = new String[size];

		for(int i = 0; i < size; i++){
			list1[i] = fileList[i];
		}
		for(int j = size; j < fileList.length; j++){
			list2[j-size] = fileList[j];
		}

		try {
			//is1 = Runtime.getRuntime().exec(new String[] {"java Parents.java", "one", ManagementFactory.getRuntimeMXBean().getName()}).getOutputStream();
			
			//is2 = Runtime.getRuntime().exec(new String[] {"Parents.java", "two", ManagementFactory.getRuntimeMXBean().getName()}).getOutputStream();
			
			//String[]cmdArray = {"Parents.java", "one", ManagementFactory.getRuntimeMXBean().getName()};
			//String[] env = {"path=;","path=" + System.getProperty("user.home")};
			//Process p1 = Runtime.getRuntime().exec(cmdArray, env);
			a1 = new Parents(list1, ManagementFactory.getRuntimeMXBean().getName());
		} catch (Exception e) {
			System.out.println("SOMETHING'S WRONG");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		Master a = new Master();
	}


}
