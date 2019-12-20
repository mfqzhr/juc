package com.mfq.n4_001_001_001;

public class ThreadLocalTest {
	
	//print函数
	static void print(String str) {
		//打印当前线程本地内存中的localVariable变量的值
		System.out.println(str + " : " + localVariable.get());
		//清除当前线程本地内存中的localVariable变量的值
		localVariable.remove();
	}
	//创建ThreadLocal的值
	static ThreadLocal<String> localVariable = new ThreadLocal<>();
	
	
	public static void main(String[] args) {
		Thread threadOne = new Thread(() -> {
			localVariable.set("threadOne local variable");
			print("threadOne");
			System.out.println("threadOne remove after" + ":" + localVariable.get());
		});
		
		Thread threadTwo = new Thread(() -> {
			localVariable.set("threadTwo local variable");
			print("threadTwo");
			System.out.println("threadTwo remove after" + ":" + localVariable.get());
		});
		threadOne.start();
		threadTwo.start();
	}
	

}
