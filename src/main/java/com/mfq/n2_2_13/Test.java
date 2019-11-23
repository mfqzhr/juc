package com.mfq.n2_2_13;

public class Test {

	public static void main(String[] args) {
		MyTest test1 = new MyTest();
		MyTest test2 = new MyTest();
		MyTest test3 = new MyTest();
		MyTest test4 = new MyTest();
		System.out.println(test1.getClass() == test2.getClass());
		System.out.println(test2.getClass() == test3.getClass());
		System.out.println(test3.getClass() == test4.getClass());
		
	}
}
