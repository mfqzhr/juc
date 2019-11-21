package com.mfq.n2_2_1;

public class Run {
	public static void main(String[] args) throws InterruptedException {
		Task task = new Task();

		MyThread1 myThread1 = new MyThread1(task);

		myThread1.start();

		MyThread1 myThread2 = new MyThread1(task);

		myThread2.start();

		Thread.sleep(10000);

		long beginTime = CommonUtils.beginTime1;

		if (CommonUtils.beginTime2 < CommonUtils.beginTime1) {

			beginTime = CommonUtils.beginTime2;

		}

		long endTime = CommonUtils.endTime1;

		if (CommonUtils.endTime2 > CommonUtils.endTime1) {

			endTime = CommonUtils.endTime2;

		}
		System.out.println(beginTime);
		System.out.println(endTime);
	}
}
