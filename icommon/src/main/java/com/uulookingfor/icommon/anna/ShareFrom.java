package com.uulookingfor.icommon.anna;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author suxiong.sx
 * 
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ShareFrom {
	
	String from() default "";
	
	boolean forceOverWrite() default true;
	
}
