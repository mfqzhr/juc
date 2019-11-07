package com.mfq.no1210;
/**
 * 不共享数据的情况
 * @author mfq
 *
 */
public class Run {

	public static void main(String[] args) {
		Mythread run = new Mythread();
		Thread t1 = new Thread(run);
		Thread t2 = new Thread(run);
		Thread t3 = new Thread(run);
		Thread t4 = new Thread(run);
		Thread t5 = new Thread(run);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
}
