# 多线程的学习

## 一. java多线程技能

#### start()方法耗时的原因是执行了多个步骤:

1. 1通过jvm告诉操作系统创建Thread

1. 2 操作系统开辟内存并且使用Windows SDK中的createTHread() 函数创建Thread对象

1. 3 操作系统对Thread方法进行调度,以确定线程的运行时机

1. 4 Thread 在操作系统中被成功执行

- 以上四个操作步骤的执行所消耗的时间一定大于输出 "运行结束" 字符串的时间

#### 判断线程是否为停止状态:



```java
	public static void main(String[] args) {
	try {
		MyThread thread = new MyThread();
		thread.start();
		thread.sleep(1000);
		thread.interrupt();
		//Thread.currentThread().interrupt();
		System.out.println("是否停止1? = " + 		                thread.interrupted());
		System.out.println("是否停止2? = " +                        thread.interrupted());
	} catch (Exception e) {
		// TODO: handle exception
	}
}
```
- Thread.interrupted() : 测试当前的线程是否已经中断. 线程中断状态由该方法清除. 换句话说, 如果连续两次调用该方法的时候, 则第二次调用将返回false(在第一次调用已经清除了其中断状态之后,且第二次调用检测完中断状态前,当前线程再次中断的情况除外)
- Thread.isInterrupted() : 方法并未清除状态标志
- 区别:
  1. this.interrupted() : 测试当前的线程是否已经中断状态,执行后具有清除状态标志值为false的功能
  2. this.isTerrupted() : 测试线程Thread对象是否已经是中断状态,不清楚状态标志

#### 暂停线程

1. 暂停线程意味着此线程还可以恢复运行,在java多线程中,可以使用suspend()方法暂停线程,使用resume()方法来恢复线程的执行

   ```java
   	public static void main(String[] args) throws InterruptedException {
   		MyThread thread = new MyThread();
   		thread.start();
   		Thread.sleep(5000);
   		//A段
   		thread.suspend();
   		System.out.println("A=" + System.currentTimeMillis() + "i=" + thread.getI());
   		Thread.sleep(5000);
   		System.out.println("A=" + 	System.currentTimeMillis() + "i=" + thread.getI());
   		//B段
   		thread.resume();
   		Thread.sleep(5000);
   		//C段
   		thread.suspend();
   		System.out.println("B=" + System.currentTimeMillis() + "i=" + thread.getI());
   		Thread.sleep(5000);
   		System.out.println("B=" + System.currentTimeMillis() + "i=" + thread.getI());
   
   	}
   //输出:
   //A=1573134549054i=2515860505
   //A=1573134554054i=2515860505
   //B=1573134559064i=4864150954
   //B=1573134564064i=4864150954
   
   ```

2. suspend()方法与resume()方法的缺点-独占

   如果suspend()方法与resume方法使用不当,极易造成公共同步对象被独占,其他线程无法访问公共同步对象的结果



#### yield()方法:

yield()方法的作用是放弃当前的cpu资源,让其他任务去占用cpu执行时间,放弃的时间不确定,有可能刚刚放弃,马上又获得cpu的时间片:

```java
public class MyThread extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		long beginTime = System.currentTimeMillis();
		int count = 0;
		for (int i = 0; i < 500000; i++) {
			//Thread.yield();
			count = count + (i + 1);
		}
		long endTime = System.currentTimeMillis();
		
		System.out.println("用时: " + (endTime - beginTime) + " 毫秒");
	}

}
//用时: 2 毫秒

public class MyThread extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		long beginTime = System.currentTimeMillis();
		int count = 0;
		for (int i = 0; i < 500000; i++) {
			Thread.yield();
			count = count + (i + 1);
		}
		long endTime = System.currentTimeMillis();
		
		System.out.println("用时: " + (endTime - beginTime) + " 毫秒");
	}

}
//用时: 277 毫秒
```

#### 线程的优先级:

​	在操作系统中,线程可以划分优先级,优先级较高的线程获得cpu资源多,也就是cpu优先执行优先级较高的线程对象中的人物,其实就是让高优先级的线程获得更多的cpu时间片.

​	设置线程优先级有助于线程规划器确定在下一次选择哪一个线程来优先执行.

​	设置线程的优先级使用setPriority()方法,此方法zaiJDK中的源代码如下:

```java
public final void setPriority(int newPriority) {
	ThreadGroup g;
	checkAccess();
	if(newPriority > MAX_PRIORITY || newPriority < MIN_PRIORITY) {
		throw new IllegalArgumentException();
	}
	if((g = getThreadGroup) != null) {
		if(newPriority > g.getMaxPriority) {
			newPriority = g.getMaxPriority();
		}
		setPriority0(priority = newPriority);
	}
}
```

​	在java中,线程的优先级分为1-10共10个等级,如果优先级小于1或者大于10,则JDK抛出异常.

​	JDK使用3个常量来预置定义优先级的值,代码如下:

```java
public final static int MIN_PRIORITY = 1;
public final static int NORM_PRIORITY = 5;
public final static int MAX_PRIORITY = 10;
```

#### 线程优先级的继承特性:

​	在java中,线程的优先级具有继承性, 例如, A线程启动B线程,则B线程的优先级与A线程是一样的.

```java
public class MyThread1 extends Thread{
	@Override
	public void run() {
		System.out.println("线程1的优先级: " + getPriority());
		MyThread2 thread = new MyThread2();
		thread.start();
	}

}

public class MyThread2 extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		System.out.println("线程2的优先级: " + getPriority());
	}

}

public class Run {
	public static void main(String[] args) {
		Thread.currentThread().setPriority(6);
		System.out.println("主线程的优先级: " + Thread.currentThread().getPriority());
		MyThread1 thread = new MyThread1();
		thread.start();
	}

}

//主线程的优先级: 6
//线程1的优先级: 6
//线程2的优先级: 6
```

#### 守护线程

​	什么是守护线程: 守护线程是一种特殊的线程,当进程中不存在非守护线程了,则守护线程自动销毁.典型的守护线程是垃圾回收线程,当进程中没有非守护线程了,则垃圾回收线程也就没有存在的必要了,自动销毁. 

```java
public class MyThread extends Thread{
	private int i = 0;
	@Override
	public void run() {
		try {
			while (true) {
				i++;
				System.out.println("i=" + i);
				Thread.sleep(1000);
				
			}
			
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}

}

public class Run {
	public static void main(String[] args) throws InterruptedException {
		MyThread thread = new MyThread();
		thread.setDaemon(true);
		thread.start();
		Thread.sleep(5000);
		System.out.println("我离开thread对象也不再打印了,也就是停止了");
		}

}
```

## 二. 对象及变量的并发访问

#### synchronized同步方法:

​	关键字synchronized可用来保障原子性 可见性 有序性

#### 方法内的变量为线程安全:

​	非线程安全问题存在于实例变量中,对于方法内部的私有变量,则不存在非线程安全问题,结果是线程安全的.

```java
public class HasSelfPrivateNum {
	public void addI(String username) {
		try {
			int num = 0;
			if (username.equals("a")) {
				num = 100;
				System.out.println("a set over");
				Thread.sleep(2000);

			} else {
				num = 200;
				System.out.println("b set over");
			}
			System.out.println(username + " num= " + num);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


public class ThreadA extends Thread {
	private HasSelfPrivateNum hasSelfPrivateNum;

	public ThreadA(HasSelfPrivateNum hasSelfPrivateNum) {
		super();
		this.hasSelfPrivateNum = hasSelfPrivateNum;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		hasSelfPrivateNum.addI("a");
	}

}


public class ThreadB extends Thread {
	private HasSelfPrivateNum hasSelfPrivateNum;

	public ThreadB(HasSelfPrivateNum hasSelfPrivateNum) {
		super();
		this.hasSelfPrivateNum = hasSelfPrivateNum;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		hasSelfPrivateNum.addI("b");
	}

}

public class Run {
	public static void main(String[] args) {
		HasSelfPrivateNum h = new HasSelfPrivateNum();
		ThreadA a = new ThreadA(h);
		a.start();
		ThreadB b = new ThreadB(h);
		b.start();
	}

}
```



#### 实例变量非线程安全问题与解决方案:

​	如果多个线程共同访问一个对象中的实例变量,则有可能出现非线程安全的问题.

​	用线程访问德 对象中如果有多个实例变量,则运行的结果可能出现交叉的情况.

​	如果对象仅有一个实例变量,则有可能出现覆盖的情况.

```java
public class HasSelfPrivateNum {
	private int num = 0;
	public void addI(String username) {
		try {
			
			if (username.equals("a")) {
				num = 100;
				System.out.println("a set over");
				Thread.sleep(2000);

			} else {
				num = 200;
				System.out.println("b set over");
			}
			System.out.println(username + " num= " + num);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


public class ThreadA extends Thread {
	private HasSelfPrivateNum hasSelfPrivateNum;

	public ThreadA(HasSelfPrivateNum hasSelfPrivateNum) {
		super();
		this.hasSelfPrivateNum = hasSelfPrivateNum;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		hasSelfPrivateNum.addI("a");
	}

}


public class ThreadB extends Thread {
	private HasSelfPrivateNum hasSelfPrivateNum;

	public ThreadB(HasSelfPrivateNum hasSelfPrivateNum) {
		super();
		this.hasSelfPrivateNum = hasSelfPrivateNum;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		hasSelfPrivateNum.addI("b");
	}

}

public class Run {
	public static void main(String[] args) {
		HasSelfPrivateNum h = new HasSelfPrivateNum();
		ThreadA a = new ThreadA(h);
		a.start();
		ThreadB b = new ThreadB(h);
		b.start();
	}

}



public class HasSelfPrivateNum {
	private int num = 0; //线程不安全
	synchronized public void addI(String username) {
		//int num = 0;//线程安全
		try {
			if (username.equals("a")) {
				System.out.println("a start");
				num = 100;
				System.out.println("a set over");
				Thread.sleep(2000);

			} else {
				System.out.println("b start");
				num = 200;
				System.out.println("b set over");
			}
			System.out.println(username + " num= " + num);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
```

结论:	

- 两个线程同时访问同一个对象中的同步方法时一定是线程安全的.
- 最小单位是线程，只有方法，进去线程的栈结构里，才算运行这个方法，那么方法的私有变量才存活，如果方法用到了对象的属性，牵扯到从堆里取出属性放入方法栈的拷贝过程。
- 因此当有多个线程，使用了同一个对象的方法。那么，方法内的变量是独立在各自线程的，但是对应的属性来自于同一个对象，对方修改可能看得到可能看不到，不一致



#### 同步synchronized在字节码指令中的原理

在方法总使用synchronized关键字实现同步的原因是使用了flag标记ACC_SYN_CHRONIZED. 当调用方法的时候,调用指令会检查方法的ACC_SYNCHRONIZED访问标志是否设置,如果设置了,执行线程先持有的同步锁,然后执行方法,最后在方法完成时释放锁.

```java
  public static synchronized void testMethod();
    descriptor: ()V
    flags: ACC_PUBLIC, ACC_STATIC, ACC_SYNCHRONIZED
    Code:
      stack=0, locals=0, args_size=0
         0: return
      LineNumberTable:
        line 6: 0

```

​	在反编译的字节码指令中,对public synchronized void testmethod() 方法使用了flag标记 ACC_SYNCHRONIZED,说明此方法是同步的.

​	如果使用synchronized代码块,则使用monitorenter和monitorexit指令进行同步处理, 测试代码如下:



```java
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
  
  
  public void myMethod();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=4, args_size=1
         0: aload_0
         1: dup
         2: astore_1
         3: monitorenter
         4: bipush        100
         6: istore_2
         7: aload_1
         8: monitorexit
         9: goto          17
        12: astore_3
        13: aload_1
        14: monitorexit
        15: aload_3
        16: athrow
        
  //字节码中使用monitorenter 和 monitorexit指令进行同步处理
```

#### 多个对象多个锁



```java
public class Run {

	public static void main(String[] args) {
		HasSelfPrivateNum num1 = new HasSelfPrivateNum();
		HasSelfPrivateNum num2 = new HasSelfPrivateNum();
		ThreadA threadA = new ThreadA(num1);	
		threadA.start();
		ThreadB threadB = new ThreadB(num2);
		threadB.start();
		
	}
}
```



#### 将synchronized方法与对象作为锁

```java
	synchronized public void methodA() {
		try {
			System.out.println("begin methodA threadName=" + Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("end");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
```

如上面的代码所示,在methodA()方法前加入了synchronized进行同步处理,调用关键字synchronized声明的方法一定是排队进行运行的.另外,需要牢牢记住"共享"这两个字,只有共享资源的读写访问才需要同步化,如果不是共享资源,那么就没有同步的必要了.



当两个线程访问同一个对象的两个同步的方法:

```java
public class MyObject {
	synchronized public void methodA() {
		try {
			System.out.println("begin methodA threadName=" + Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("end");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	synchronized public void methodB() {
		try {
			System.out.println("begin methodA threadName=" + Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("end");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
//运行如下:
//begin methodA threadName=a
//end
//begin methodA threadName=b
//end


```

​	结论:

1. A线程先持有object对象的Lock锁,B线程可以以异步调用的方式调用object对象中的非synchronized类型的方法.
2. A线程先持有object对象的Lock锁,B线程如果在这个时候调用object对象中的synchronized类型的方法,则需要等待,也就是同步.
3. 在方法申明处添加synchronized并不是锁方法,而是锁当前类的对象.
4. 在java中只有"将对象作为锁"这种说法,并没有"锁方法"这个说法.
5. 在java语言中,"锁"就是"对象","对象"也可以映射成"锁",哪个线程拿到这个锁,哪个线程就可以执行这个对象中的synchronized同步方法.
6. 如果在X对象中使用了synchronized关键字申明非静态方法,则X对象就被当做锁

### 脏读

