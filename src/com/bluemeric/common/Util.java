package com.bluemeric.common;

import java.io.File;
import java.util.Arrays;

public class Util {

	/**
	 * @param args
	 */
	public static String[] readDirectories(String dir){
		File file = new File(dir);
		String[] directories = file.list();
		System.out.println(Arrays.toString(directories));
		return directories;	
	}
}
