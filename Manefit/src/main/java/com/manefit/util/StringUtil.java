package com.manefit.util;

public class StringUtil {
	public static boolean isNull(String data){
		boolean result = false;
		if(data == null || data.length()==0){
			result = true;
		}
		return result;
	}
	
	
	public static String ToBR(String data){
		if(isNull(data)){
			return null;
		}
		else{
			data = data.replaceAll("\r\n", "<br>");
			return data;
		}
	}
}
