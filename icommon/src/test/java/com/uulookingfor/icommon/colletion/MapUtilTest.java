package com.uulookingfor.icommon.colletion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.uulookingfor.icommon.collection.MapUtil;
import com.uulookingfor.icommon.collection.MapUtil.MergeCallback;
import com.uulookingfor.icommon.collection.MapUtil.SplitCallBack;

public class MapUtilTest {
	
	public static class Test{
		
		public String sParam ;
		
		public Long lParam ;
		
		public Test(String str, Long l){
			this.sParam = str;
			this.lParam = l;
		}
		
		public String toString(){
			return sParam + "-" +String.valueOf(lParam);
		}
		
	}
	public static void main(String[] args){
		List<Test> list = new ArrayList<Test>();
		
		list.add(new Test("11", 1L));
		list.add(new Test("11", 11L));
		list.add(new Test("22", 2L));
		list.add(new Test("33", 3L));
		list.add(new Test("44", 4L));
		list.add(new Test("55", 5L));
		
		list.add(new Test("55", 15L));
		list.add(new Test("55", 25L));
		
		Map<String, List<Test>> map = MapUtil.splitToMap(list, new SplitCallBack<String, Test>(){

			public String genKey(Test val) {

				return val.sParam;
			}
			
		});
		
		System.out.println(map);
		
		Map<String, Test> map2 = MapUtil.mergeBySameKey(list, new MergeCallback<String, Test>(){

			public String genKey(Test obj) {
				return obj.sParam;
			}

			public Test merge(Test oldOne, Test newOne) {
				return oldOne;
			}
			
		});
		
		System.out.println(map2);
		
	}
}
