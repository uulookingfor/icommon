package com.uulookingfor.icommon.util;

import java.lang.reflect.Field;
import java.util.Collection;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

import com.uulookingfor.icommon.anna.NotEmptyField;
import com.uulookingfor.icommon.anna.NotNullField;
import com.uulookingfor.icommon.anna.ShareFrom;
import com.uulookingfor.icommon.anna.ToCollection;


/**
 * @author suxiong.sx
 */
public class PreCondAndHandle {
	
	//boolean isAccess = field.isAccessible();
	public static <T> void fieldNotNullOrEmptyCheck(final T obj){
		if(obj == null){
			throw new NullPointerException();
		}
		
		ReflectionUtils.doWithFields(obj.getClass(), new FieldCallback(){

			@Override
			public void doWith(Field field) throws IllegalArgumentException,
					IllegalAccessException {

				boolean isAccess = field.isAccessible();
				try{
					field.setAccessible(true);
					doWith0(field);
				}finally{
					field.setAccessible(isAccess);
				}
			}
			
			private void doWith0(Field field) throws IllegalArgumentException,
					IllegalAccessException {
				
				Object val = field.get(obj);
				
				if(AnnotationUtils.findAnnotation(field, NotNullField.class) != null){
					if(val == null){
						throw new NullPointerException(field.getName());
					}
				}
				
				if(AnnotationUtils.findAnnotation(field, NotEmptyField.class) != null){
					if(val == null){
						throw new NullPointerException(field.getName());
					}
					if(val instanceof Collection<?>){
						if(((Collection<?>) val).isEmpty()){
							throw new IllegalArgumentException(field.getName() + " can't be empty");
						}
					}else{
						//NotEmptyField does not work on field which not implemented interface Conllection
					}
				}
				return;
			}
			
		});
	}
	
	public static <T> void preHandle(final T obj){
		
		if(obj == null){
			throw new NullPointerException();
		}
		
		ReflectionUtils.doWithFields(obj.getClass(), new FieldCallback(){

			@Override
			public void doWith(Field field) throws IllegalArgumentException,
					IllegalAccessException {

				boolean isAccess = field.isAccessible();
				try{
					field.setAccessible(true);
					doWith0(field);
				}finally{
					field.setAccessible(isAccess);
				}
			}
			
			private void doWith0(Field field) throws IllegalArgumentException,
					IllegalAccessException {
				
				Object val = field.get(obj);
				
				ToCollection toCollectionAnna;
				if((toCollectionAnna = AnnotationUtils.findAnnotation(field, ToCollection.class)) != null){
					if(val != null){
						addCollectionElem(toCollectionAnna, val);
					}
				}
				
				ShareFrom shareFromAnna;
				if((shareFromAnna = AnnotationUtils.findAnnotation(field, ShareFrom.class)) != null){
					if(shareFromAnna.forceOverWrite() || val == null){
						shareFrom(shareFromAnna, field);
					}
				}
				
				return;
			}
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			private void addCollectionElem(ToCollection toCollectionAnna, Object val) throws IllegalArgumentException, IllegalAccessException{
				
				String dest = toCollectionAnna.dest();
				Field destField = ReflectionUtils.findField(obj.getClass(), dest);
				if(destField != null){

					boolean isAccess = destField.isAccessible();
					try{
						destField.setAccessible(true);
						Object destVal = destField.get(obj);
						if(destVal instanceof Collection){
							((Collection) destVal).add(val);
						}
					}finally{
						destField.setAccessible(isAccess);
					}
				}
					
			}
			
			private void shareFrom(ShareFrom shareFromAnna, Field field) throws IllegalArgumentException, IllegalAccessException{
				
				String from = shareFromAnna.from();
				Field fromField = ReflectionUtils.findField(obj.getClass(), from);
				if(fromField != null){

					boolean isAccess = fromField.isAccessible();
					try{
						fromField.setAccessible(true);
						Object fromVal = fromField.get(obj);
						field.set(obj, fromVal);
					}finally{
						fromField.setAccessible(isAccess);
					}
				}
					
			}
			
		});
		
	}

}
