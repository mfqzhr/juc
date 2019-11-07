package com.mythread.www;

/**
 * start()方法耗时的原因是执行了多个步骤:
 * 1. 通过jvm告诉操作系统创建Thread
 * 2. 操作系统开辟内存并且使用Windows SDK中的createTHread() 函数创建Thread对象
 * 3. 操作系统对Thread方法进行调度,以确定线程的运行时机
 * 4. Thread 在操作系统中被成功执行
 * 以上四个操作步骤的执行所消耗的时间一定大于输出 "运行结束" 字符串的时间
 *
 */
public class Run {
	public static void main(String[] args) {
		MyThread myThread = new MyThread();
		myThread.start();//耗时大
		System.out.println("运行结束");//耗时小
	}

}
