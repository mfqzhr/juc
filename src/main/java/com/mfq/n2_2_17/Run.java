package com.mfq.n2_2_17;

public class Run {

	public static void main(String[] args) {
		Service service = new Service();
		ThreadA a = new ThreadA(service);
		a.start();
		ThreadB b = new ThreadB(service);
		b.start();
	}
	
	
}
