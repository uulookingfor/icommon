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
public class MapUtil<K, V> {
	
	public static <K, V> Map<K, List<V>> splitToMap(
			@NonNull List<V> list, 
			@NonNull SplitCallBack<K, V> callBack){
		
		Map<K, List<V>> ret = new HashMap<K, List<V>>();
		
		for(V val : list){
			if(val == null){
				throw new RuntimeException("list contain null");
			}
			
			K key = callBack.genKey(val);
			
			if(ret.get(key) != null){
				ret.get(key).add(val);
			}else{
				List<V> newList = new ArrayList<V>();
				newList.add(val);
				
				ret.put(key, newList);
			}
		}
		
		return ret;
	}
	
	public static interface SplitCallBack<K, V>{
		
		K genKey(V val);
	}
	
	public static <K, V> Map<K, V> mergeBySameKey(List<V> list, MergeCallback<K, V> callback){
		
		Map<K, V> retMap = new HashMap<K, V>();
		
		for(V obj : list) {
			if(obj == null){
				continue;
			}
			K key = callback.genKey(obj);
			if(retMap.containsKey(key)){
				retMap.put(key, callback.merge(retMap.get(key), obj));
			} else {
				retMap.put(key, obj);
			}
		}
		
		
		return retMap;
	}
	
	public static interface MergeCallback<K, V>{
		K genKey(V obj);
		V merge(V oldOne, V newOne);
	}
	
	
}
