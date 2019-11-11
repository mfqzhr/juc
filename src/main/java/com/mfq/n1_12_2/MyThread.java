package com.mfq.n1_12_2;

public class MyThread extends Thread{
	private int i = 0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while (true) {
			i++;
			System.out.println(i);
		}
	}
}
