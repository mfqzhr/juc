package com.mfq.a_ma.c_013;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.LockSupport;

class A{
	public  synchronized void print(char c) {
		System.out.print((char)c++);
	}
}
class B{
	public  synchronized void print(int i) {
		System.out.print(i++);
	}
}
public class T014 {

	static Thread a;
	static Thread b;

	// 要求顺序打印A1B2C3B4
	public static void main(String[] args) {

		a = new Thread(() -> {
			char c = 'A';
			for (int i = 0; i < 26; i++) {
				System.out.print((char) c++);
				LockSupport.unpark(b);
				LockSupport.park();
				
			}
		});
		b = new Thread(() -> {
			for (int i = 1; i < 27; i++) {
				System.out.print(i);
				LockSupport.unpark(a);
				LockSupport.park();

			}
		});

		a.start();
		try {
			TimeUnit.MICROSECONDS.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		b.start();
	}
}
