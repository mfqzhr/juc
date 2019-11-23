package com.mfq.n2_2_2;

public class Task {

	public void doLongTimeTask() {
		//异步执行
		for (int i = 0; i < 100; i++) {
			System.out.println("nosynchronized threadName=" + Thread.currentThread().getName() + "i=" + (i + 1));
		}
		System.out.println("");
		//同步执行
		synchronized (this) {
			for (int i = 0; i < 100; i++) {
				System.out.println("synchronized threadName=" + Thread.currentThread().getName() + "i=" + (i + 1));
			}
		}

	}

}
