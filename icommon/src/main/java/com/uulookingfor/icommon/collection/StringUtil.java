package com.uulookingfor.icommon.collection;

public class StringUtil {
	
	public static boolean emptyString(String str){
		
		if(str == null){
			return true;
		}
		
		if("".equals(str)){
			return true;
		}
		
		if("".equals(str.trim())){
			return true;
		}
		
		return false;
	}
	
}
