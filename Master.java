package pkg1;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;

public class Master {

	private String[] fileList;
	private Parents p1, p2;

	public Master(){
		System.out.println(ManagementFactory.getRuntimeMXBean().getName());
		//fileList = new String[new File(System.getProperty("user.home") +"/mok/CS452/project_one/").listFiles().length];
		if(!setupFiles()){
			System.out.println("There were no files found in the directory.  Exiting Program");
			System.exit(0);
		}
		split();
		



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
		/**int size = (new File(System.getProperty("user.home") +"/mok/CS452/project_one/").listFiles().length)/2;
		String[] list1 = new String[size];
		String[] list2 = new String[size];

		for(int i = 0; i < size; i++){
			list1[i] = fileList[i];
		}
		for(int j = size; j < fileList.length; j++){
			list2[j-size] = fileList[j];
		}*/

		try {
			Runtime.getRuntime().exec(new String[] {"java Parents.java", "one", ManagementFactory.getRuntimeMXBean().getName()});
			Runtime.getRuntime().exec(new String[] {"java Parents.java", "two", ManagementFactory.getRuntimeMXBean().getName()});
		} catch (IOException e) {
			System.out.println("SOMETHING'S WRONG");
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		Master a = new Master();
	}


}
