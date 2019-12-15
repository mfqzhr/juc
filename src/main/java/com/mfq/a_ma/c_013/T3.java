package com.mfq.a_ma.c_013;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 锁
 * @author ac‘
 *
 */
public class T3 {
	private static Lock lock = new ReentrantLock();
	public static void main(String[] args) {
		T3 t3 = new T3();
		new Thread(t3::fun).start();
		
		
	}
	public void fun() {
		try {
			lock.lock();
			System.out.println("lock");
		} finally {
			// TODO: handle finally clause
			lock.unlock();
		}
	}

}
