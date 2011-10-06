package pkg1;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedOutputStream;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Child extends Process implements Runnable{
	private int[] nums;
	private int pid;
	private ByteArrayOutputStream out = new ByteArrayOutputStream();
	private String fileName = "";

	public Child(String pFileName, String pPid){
		fileName = pFileName;
		pid = Integer.parseInt(pPid.substring(0,pPid.indexOf('@')));
		hi(fileName);

		if(!readFile(fileName)){
			System.out.println("There was a problem reading in the file");
		}
		else{
			Arrays.sort(nums);
			String tmp = setupOutputData();
			System.out.println("Writing this " +tmp);
			try {
				out.write(tmp.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Takes in the file name and attempts to read in the ints from the
	 * file into an int array.
	 *  
	 * @param fileName
	 * @return retVal: successful or not.
	 */
	private boolean readFile(String fileName){
		boolean retVal = true;
		ArrayList<Integer> temp = new ArrayList<Integer>();
		BufferedReader inReader = null;
		FileReader inFile = null;

		try {
			inFile = new FileReader(new File(System.getProperty("user.home") + "/texts/" +fileName));
			inReader = new BufferedReader(inFile);
		} catch (FileNotFoundException e) {
			retVal = false;
			e.printStackTrace();
		}
		inReader = new BufferedReader(inFile);
		String s = "";
		try {
			s = inReader.readLine();
		} catch (IOException e) {
			retVal = false;
			e.printStackTrace();
		}


		while(s!=null){
			int num = Integer.parseInt(s);
			System.out.println("Next int: " + num);
			temp.add(num);
			try {
				s = inReader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

	public String getFileName(){
		return fileName;
	}

	private void hi(String f){
		System.out.println("Child created.  I will sort file " + f);
	}


	public ByteArrayOutputStream getOutputStream() {
		// TODO Auto-generated method stub
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
