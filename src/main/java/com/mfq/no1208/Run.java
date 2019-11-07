package com.mfq.no1208;
/**
 * 不共享数据的情况
 * @author mfq
 *
 */
public class Run {

	public static void main(String[] args) {
		/**
		 * 不共享数据的情况
		 */
		MyThread thread1 = new MyThread("A");
		MyThread thread2 = new MyThread("B");
		MyThread thread3 = new MyThread("C");
		//thread1.start();
		//thread2.start();
		//thread3.start();
		/**
		 * 共享数据的情况
		 * 出现非线程安全的问题是因为在jvm中,count--的步骤要分解为如下3
		 * 步:
		 * 1. 获取原有的count值
		 * 2. 计算 count - 1
		 * 3. 对count进行赋值
		 */
		
		/**
		  MyThread2 thread = new MyThread2(); 
		  Thread a = new Thread(thread, "A");
		  Thread b = new Thread(thread, "B"); 
		  Thread c = new Thread(thread, "C");
		  Thread d = new Thread(thread, "D"); 
		  Thread e = new Thread(thread, "E");
		  a.start(); 
		  b.start(); 
		  c.start(); 
		  d.start(); 
		  e.start();
		*/ 
		
		MyThread3 threadSync = new MyThread3();
		Thread a1 = new Thread(threadSync, "A");
		Thread b1 = new Thread(threadSync, "B");
		Thread c1 = new Thread(threadSync, "C");
		Thread d1 = new Thread(threadSync, "D");
		Thread e1 = new Thread(threadSync, "E");
		a1.start();
		b1.start();
		c1.start();
		d1.start();
		e1.start();
		
	}
}
