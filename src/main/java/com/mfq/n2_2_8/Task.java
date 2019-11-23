package com.mfq.n2_2_8;

import java.util.Iterator;

public class Task {

	public void otherMethod() {
		System.out.println("-----------run--otherMethod");
	}
	
	public void doLongTimeTask() {
		synchronized (this) {
			for (int i = 0; i < 1000; i++) {
				System.out.println("sunchronized threadName=" +
			Thread.currentThread().getName() + "i=" + (i + 1));
			}
			
		}
	}
}
