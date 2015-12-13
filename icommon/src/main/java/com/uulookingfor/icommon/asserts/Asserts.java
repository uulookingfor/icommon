package com.uulookingfor.icommon.asserts;

/**
 * @author suxiong.sx 
 */
public class Asserts {
	
	public static void notNull(Object obj, String message){
		if(obj == null){
			throw new NullPointerException(message);
		}
	}
	
	public static void notFalse(boolean bool, String message){
		if(!bool){
			throw new IllegalArgumentException(message);
		}
	}
	
}