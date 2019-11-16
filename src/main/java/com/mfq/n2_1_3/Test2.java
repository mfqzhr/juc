package com.mfq.n2_1_3;

public class Test2 {
	public void myMethod() {
		synchronized (this) {
			int age = 100;
		}
	}
	
	public static void main(String[] args) {
		Test2 test = new Test2();
		test.myMethod();
	}

}
