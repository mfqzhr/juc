#### volatile

volitile并不能保证多个线程共同修改running变量时所带来的不一致问题,也就是volitile不能替代synchronized

```java
public class T {
	volatile int count = 0;
	void m() {
		for (int i = 0; i < 10000; i++) {
			count++;
		}
	}
	public static void main(String[] args) {
		T t = new T();
		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(t::m,"thread-" + i));
			
		}
		threads.forEach((o) -> o.start());
		threads.forEach((o) -> {
			try {
				o.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		System.out.println(t.count);
	}

}
```

不要用String类型作为锁,因为在JVM中具有String常量池(如果两个String具有相同的值，那么他们的地址是相同的，都保存在这个常量池中)。当以String作为锁的时候，如果值相同则，那么线程持有相同的锁。这样就造成了另外一个线程不能执行



#### CAS(无锁优化 自旋)

AtomicXXX类本身方法都是原子性的,但不能保证多个方法同时调用是原子性的

#### LongAdder

刚开始是0,如果有一千个线程,它会分段锁,分成四个,每一段向上递增,最后一加,返回总数

#### lock



#### ReadWriteLock

读写锁的概念就是 共享锁和拍他锁

1. 共享锁:当读线程进来的时候一起读
2. 拍他锁: 当线程正在读不允许写 互斥

```java
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

```



#### Semaphore

​	限流操作

```java
import java.util.concurrent.Semaphore;

public class T5 {
	public static void main(String[] args) {
		Semaphore s = new Semaphore(1);
		new Thread(() -> {
			try {
				s.acquire();
				System.out.println("t1 running");
				Thread.sleep(200);
				System.out.println("t1 running");
				s.release();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}).start();
		new Thread(() -> {
			try {
				s.acquire();
				System.out.println("t2 running");
				Thread.sleep(200);
				System.out.println("t2 running");
				s.release();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}).start();
	}

}

```

#### 锁的四种状态

1. 无锁: 
2. 偏向锁: 只有一个线程
3. 轻量级锁: 
4. 重量级锁: 

#### LockSupport



