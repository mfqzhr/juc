package com.mfq.n1_15;

public class Run {
	public static void main(String[] args) throws InterruptedException {
		MyThread thread = new MyThread();
		thread.setDaemon(true);
		thread.start();
		Thread.sleep(5000);
		System.out.println("我离开thread对象也不再打印了,也就是停止了");
		}

}
