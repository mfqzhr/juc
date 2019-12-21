package com.mfq.a_ma.c_013;

/**
 * 生产者消费者问题
 * @author mfq
 *
 */
class AA {
	private int count;
	public synchronized void put() {
		if (count == 10) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		count++;
		this.notifyAll();
	}
	
	public synchronized int get() {
		if (count == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.notifyAll();
		return --count;
	}
}
public class T015 {

	public static void main(String[] args) {
		AA a = new AA();
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				a.put();
				System.out.println("put" + (i + 1));
			}
		}).start();
		
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
			
				System.out.println("get" + a.get());
			}
		}).start();
	}
}
