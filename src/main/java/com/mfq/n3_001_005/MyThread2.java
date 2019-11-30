package com.mfq.n3_001_005;

public class MyThread2 extends Thread{

	private Object lock;
	public MyThread2(Object lock) {
		// TODO Auto-generated constructor stub
		this.lock = lock;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		synchronized (lock) {
			System.out.println("start notify time=" + System.currentTimeMillis());
			lock.notify();
			System.out.println("end notify time=" + System.currentTimeMillis());
		}
	}
}
