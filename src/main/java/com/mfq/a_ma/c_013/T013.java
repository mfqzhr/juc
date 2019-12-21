package com.mfq.a_ma.c_013;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class T013 {

	public static void main(String[] args) {
		Thread t = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.println(i);
				if (i == 5) {
					//当前线程停止
					LockSupport.park();
				}
				try {
						
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t.start();
		
		try {
			TimeUnit.SECONDS.sleep(8);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("after 8 seconds");
		LockSupport.unpark(t);
	}
}
