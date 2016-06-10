package com.uulookingfor.icommon.serial;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author suxiong.sx
 */
public class JsonUtilTest {

	public static class Card{
		
		@Getter @Setter private String cardCode;
		
		@Getter @Setter private Long cardId;
		
		public Card(Long cardId, String cardCode){
			
			this.cardId = cardId;
			
			this.cardCode = cardCode;
			
		}
	}
	
	public static void main(String[] args){
		
		Card c1 = new Card(1L, "code1");
		
		Map<String, Card> toJson = new HashMap<String, Card>();
		toJson.put("c1", c1);
		toJson.put("c2", c1);
		
		for(Map.Entry<String, Card> oneCard : toJson.entrySet()){
			
			System.out.println("toJson, one card hash value:" + oneCard.getValue().hashCode());
			
		}
		
		String jsonStr = JsonUtil.fastJsonToString(toJson);
		
//		System.out.println(jsonStr);
		
		Map<String, Object> fromJson = JsonUtil.fastJsonToObject(jsonStr, Map.class);
		
		for(Map.Entry<String, Object> oneCard : fromJson.entrySet()){
			
			System.out.println("fromJson, one card hash value:" + oneCard.getValue().hashCode());
			
		}
		
		
	}
}
