## volatile

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



## CAS(无锁优化 自旋)

AtomicXXX类本身方法都是原子性的,但不能保证多个方法同时调用是原子性的