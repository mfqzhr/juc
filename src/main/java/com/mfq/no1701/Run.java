package com.mfq.no1701;
/**
 * 
 * @author mfq
 *
 */
public class Run {

	public static void main(String[] args) throws InterruptedException {
		Mythread mythread = new Mythread();
		System.out.println("begin=" + mythread.isAlive());
		mythread.start();
		mythread.sleep(1000);
		System.out.println("end=" + mythread.isAlive());
	}
}
