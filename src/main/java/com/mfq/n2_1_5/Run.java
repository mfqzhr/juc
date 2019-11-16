package com.mfq.n2_1_5;

public class Run {
	public static void main(String[] args) {
		MyObject object = new MyObject();
		ThreadA a = new ThreadA(object);
		a.setName("a");
		ThreadB b = new ThreadB(object);
		b.setName("b");
		a.start();
		b.start();
	}

}
