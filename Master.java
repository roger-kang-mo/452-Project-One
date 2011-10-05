package pkg1;

import java.io.File;

public class Master {

	private String[] fileList;
	private Parents p1, p2;
	// oranges
	private PipedReader 
	
	public Master(){
		//fileList = new String[new File(System.getProperty("user.home") +"/mok/CS452/project_one/").listFiles().length];
		if(!setupFiles()){
			System.out.println("There were no files found in the directory.  Exiting Program");
			System.exit(0);
		}
		
		
		
	}
	
	private boolean setupFiles(){
		boolean retValue = true;
		fileList = (new File(System.getProperty("user.home") + "/mok/CS452/project_one/texts/").list());
		if(fileList == null){
			retValue = false;
		}
		
		return retValue;
	}
	
	private void split(){
		int size = (new File(System.getProperty("user.home") +"/mok/CS452/project_one/").listFiles().length)/2;
		String[] list1 = new String[size];
		String[] list2 = new String[size];
		
		for(int i = 0; i < size; i++){
			list1[i] = fileList[i];
		}
		for(int j = size; j < fileList.length; j++){
			list2[j-size] = fileList[j];
		}
		
		p1 = new Parents(list1);
		p2 = new Parents(list2);
		
	}
}
