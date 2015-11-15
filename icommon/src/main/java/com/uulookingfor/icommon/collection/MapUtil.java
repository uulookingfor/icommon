package com.uulookingfor.icommon.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.NonNull;

/**
 * @author suxiong.sx
 * 
 */
public class MapUtil<T, Q> {
	
	public static <T, Q> Map<Q, List<T>> splitToMap(
			@NonNull List<T> list, 
			@NonNull SplitCallBack<T, Q> callBack){
		
		Map<Q, List<T>> ret = new HashMap<Q, List<T>>();
		
		for(T t : list){
			if(t == null){
				throw new RuntimeException("list contain null");
			}
			
			Q key = callBack.genKey(t);
			
			if(ret.get(key) != null){
				ret.get(key).add(t);
			}else{
				List<T> newList = new ArrayList<T>();
				newList.add(t);
				
				ret.put(key, newList);
			}
		}
		
		return ret;
	}
	
	public static interface SplitCallBack<T, Q>{
		
		Q genKey(T t);
	}
	
	public static <Q, T> Map<Q, T> mergeBySameKey(List<T> list, MergeCallback<T, Q> callback){
		
		Map<Q, T> retMap = new HashMap<Q, T>();
		
		for(T obj : list) {
			if(obj == null){
				continue;
			}
			Q key = callback.genKey(obj);
			if(retMap.containsKey(key)){
				callback.merge(retMap.get(key), obj);
			} else {
				retMap.put(key, obj);
			}
		}
		
		
		return retMap;
	}
	
	public static interface MergeCallback<T, Q>{
		Q genKey(T obj);
		T merge(T dst, T src);
	}
	
	public static void main(String[] args){
		
	}
}
