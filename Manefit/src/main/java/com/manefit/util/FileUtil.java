package com.manefit.util;

import java.io.File;

public class FileUtil {
public static String renameFile(String path, String oriName){
		
		String tempName = oriName;
		
		File tempFile = new File(path,tempName);
		
		int index = 0;
		
		while(tempFile.exists()){
			int pos = oriName.lastIndexOf(".");
			String fileName = oriName.substring(0,pos);
			String ext = oriName.substring(pos+1);
			
			index = index + 1;
			fileName = fileName + "_" + index;
			
			tempName = fileName + "." + ext;					
			
			tempFile = new File(path,tempName);
		}
		return tempName;
	}
}
