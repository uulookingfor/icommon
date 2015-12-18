package com.uulookingfor.icommon.queue;

import java.nio.ByteBuffer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import lombok.Getter;

/**
 * @author suxiong.sx 
 */
public class ByteBufferBlockingQueue {
	
	private BlockingQueue<ByteBuffer> byteBuffs;
	
	@Getter private int queueSize;
	
	@Getter private int byteBufferSize;
	
	public void inQueue(ByteBuffer item){
		try {
			item.clear();
			byteBuffs.put(item);
		} catch (InterruptedException e) {
			throw new RuntimeException("actually the byteBuffs should not full here ");
		}
	}
	
	public ByteBuffer deQueue(){
		
		ByteBuffer buffer = null;
		
		try {
			buffer = byteBuffs.take();
		} catch (InterruptedException e) {
			throw new RuntimeException("actually the byteBuffs should empty here, but emtpy");
		}
		
		return buffer;
	}
	
	public ByteBufferBlockingQueue(int capacity, int byteBufferSize){
		
		byteBuffs = new ArrayBlockingQueue<ByteBuffer>(capacity);
		
		this.queueSize = capacity;
		
		this.byteBufferSize = byteBufferSize;
		
		initBlockingQueue();
		
	}
	
	private void initBlockingQueue(){
		
		for(int i=0; i< queueSize; i++){
			inQueue(ByteBuffer.allocate(byteBufferSize));
		}
		
	}
	
}
