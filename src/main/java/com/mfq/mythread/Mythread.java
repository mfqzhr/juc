package com.mfq.mythread;

public class Mythread extends Thread{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10000; i++) {
			System.out.println("run=" + Thread.currentThread().getName());
		}
	}
}
