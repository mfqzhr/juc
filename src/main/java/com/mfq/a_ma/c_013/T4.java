package com.mfq.a_ma.c_013;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

/**
 * 读写锁
 * 
 * @author ac‘
 *
 */
public class T4 {
	static Lock lock = new ReentrantLock();
	private static int value;
	static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	static Lock readLock = readWriteLock.readLock();
	static Lock writeLock = readWriteLock.writeLock();

	public static void read(Lock lock) {
		lock.lock();
		try {
			Thread.sleep(1000);
			System.out.println("reaed over");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	public static void write(Lock lock, int v) {
		lock.lock();
		try {
			Thread.sleep(1000);
			value = v;
			System.out.println("write over");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		
		for (int i = 0; i < 18; i++) {
			new Thread(() -> read(readLock)).start();
		}
		for (int i = 0; i < 2; i++) {
			new Thread(() -> write(writeLock, new Random().nextInt(10))).start();
			
		}
		
	}

}
