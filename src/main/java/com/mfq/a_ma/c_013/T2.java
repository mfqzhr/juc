package com.mfq.a_ma.c_013;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class T2 {
	
	private static Long count1 = 0L;
	private static AtomicLong count2 = new AtomicLong(0L);
	private static LongAdder count3 = new LongAdder();
	private final static Object lock = new Object();
	public static void main(String[] args) {
		Thread[] threads = new Thread[1000];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				for (int j = 0; j < 100000; j++) {
					//count2.incrementAndGet();//2033
					//count3.increment();//503
					synchronized (lock){
						count1++;//6230
					}
				}
			});
		}
		
		long start = System.currentTimeMillis();
		for (Thread thread : threads) {
			thread.start();
		}
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(count1);
		System.out.println(end - start);
		
	}

}
