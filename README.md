# 多线程的学习

一. java多线程技能

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

   #### synchronized锁重入

关键字synchronized拥有重入锁的功能,即在使用synchronized时,当一个线程得到一个对象的锁后,再次请求对象锁时是可以得到该对象锁的,这也证明在一个synchronized方法/块的内部调用本类的其他synchronized方法/块时,是永远可以得到锁的.

```java
public class Service {
	synchronized public void service1() {
		System.out.println("service1");
		service2();
	}
	
	synchronized public void service2() {
		System.out.println("service2");
		service3();
	}
	
	synchronized public void service3() {
		System.out.println("service3");
	}

}

```

"可重入锁"是指自己可以再次获取自己的内部锁,例如,**一个线程获得了某个对象锁,此时这个对象锁还没有释放,当其再次想要获取这个对昂锁时还是可以获取的,如果不可重入锁,则方法service2()不会被调用,方法service3()更不会执行**

#### 锁重入支持继承的环境

所重入也支持父子类继承的环境.

```java
public class Sub extends Main{
	synchronized public void operateISubMethod() {
		try {
			while (i > 0) {
				i--;
				System.out.println("Sub pring i=" + i);
				Thread.sleep(100);
				super.operateIMainMethod();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

public class Main {
	public int i = 10;
	
	synchronized public void operateIMainMethod() {
		try {
			i--;
			System.out.println("main pring i=" + i);
			Thread.sleep(100);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}

//输出
/**
Sub pring i=9
main pring i=8
Sub pring i=7
main pring i=6
Sub pring i=5
main pring i=4
Sub pring i=3
main pring i=2
Sub pring i=1
main pring i=0
*/
```



#### 出现异常,锁自动释放

当一个线程执行的代码出现异常时,其所持有的锁会自动释放.

#### 重写方法不使用synchronized

重写方法不适用synchronized关键字,即是非同步方法,使用后编程同步方法

#### synchronized同步代码块

用关键字synchronized声明方法在某些情况下是有弊端的,例如,A线程调用同步方法执行一个长时间的任务,那么B线程等待的时间就比较长,这种情况可以使用synchronized同步语句块来解决,一提高运行效率.

#### synchronized方法的弊端

耗时

#### 一半同步,一半异步

在synchronized中就是同步执行,不在synchronized中就是异步执行

```java
	public void doLongTimeTask() {
		//异步执行
		for (int i = 0; i < 100; i++) {
			System.out.println("nosynchronized threadName=" + Thread.currentThread().getName() + "i=" + (i + 1));
		}
		System.out.println("");
		//同步执行
		synchronized (this) {
			for (int i = 0; i < 100; i++) {
				System.out.println("synchronized threadName=" + Thread.currentThread().getName() + "i=" + (i + 1));
			}
		}

	}
```



#### 同步synchronized(this)代码块是锁定当前对象的

和synchronized方法一样 synchronized(this)代码块也是锁定当前对象的.



#### 将任意对象作为锁

多个线程调用同一个对象中的不同名称的synchronized同步方法或者synchronized(this)同步代码块时,调用的效果是按顺序执行,即同步.

synchronized同步方法或者synchronized(this)同步代码块分别有两种作用.

- synchronized同步方法的作用
  1. 对其他synchronized同步方法 或者synchronized(this)同步代码块调用呈同步效果.
  2. 同一时间只有一个线程可以执行synchronized同步方法中的代码
- synchronized同步代码块的作用
  1. 对其他synchronized同步方法或者synchronized(this)代码块呈同步效果
  2. 同一时间只有一个线程可以执行synchronized(this)同步代码块中的代码

锁非this对象有一定的优点: 如果一个类中有很多个synchronized方法,则这时虽然能实现同步,但是影响运行效率,如果使用同步代码块锁非this对象,则synchronized(非this)代码块中的程序与同步方法是异步的,因为有两把锁,不与其他锁this同步方法争抢this锁,可以大大提高运行效率.



#### 类Class单例性

每一个 *.java文件对应Class类的实例都是一个,在内存中是单例的,测试代码如下

```java
	public static void main(String[] args) {
		MyTest test1 = new MyTest();
		MyTest test2 = new MyTest();
		MyTest test3 = new MyTest();
		MyTest test4 = new MyTest();
		System.out.println(test1.getClass() == test2.getClass());
		System.out.println(test2.getClass() == test3.getClass());
		System.out.println(test3.getClass() == test4.getClass());
		
	}
	
```



Class类用于描述类的基本信息司,包括有多少个字段,有多少个构造方法,有多少个普通方法等,为了减少对内存的高占用率,在内存中只需要存一份Class类对象就可以了,所以被设计是单例的.

#### 静态同步synchronized方法与synchronized(class)代码块

关键字synchronized还可以应用在static静态方法上,如果这样写,那是对当前的 *.java文件对应的Class类对象进行加锁, Class类的对象是单例的,更具体地说,在静态static方法上使用synchronized关键字声明同步方法时,使用当前静态方法所在类对应Class类的单例对象作为锁



#### String常量池特性与同步相关的问题与解决方案

jvm具有String常量池的功能

```java
public class Test {
	public static void main(String[] args) {
		String a = "a";
		String b = "a";
		System.out.println(a == b);
	}
}

```

   当将synchronized(String)同步块与String联合使用时，要注意常量池会带来一些意外.

```java
public class Service {
	public static void print(String stringParam) {
		// TODO Auto-generated method stub
		synchronized (stringParam) {
			while (true) {
				System.out.println(Thread.currentThread().getName());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}

public class ThreadA extends Thread{
	private Service service;
	public ThreadA(Service service) {
		// TODO Auto-generated constructor stub
		this.service = service;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		service.print("AA");
	}
}


public class ThreadB extends Thread{
	private Service service;
	public ThreadB(Service service) {
		// TODO Auto-generated constructor stub
		this.service = service;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		service.print("AA");
	}
}


public class Run {

	public static void main(String[] args) {
		Service service = new Service();
		ThreadA a = new ThreadA(service);
		a.start();
		ThreadB b = new ThreadB(service);
		b.start();
	}
	
	
}


```

执行结果都是Thread-0,出现这种结果是因为两个线程持有相同的锁,造成B贤臣不能执行.这就是String常量池所带来的问题.

#### 多线程的死锁

java多线程死锁是因为不同的线程都在等待根本不可能被释放的锁,从而导致所有的任务都无法继续完成.在多线程技术中, "死锁"是必须避免的,因为这会造成线程"假死".

#### 同步写法案例比较

类对象应该指类的Class对象,也就是字节码对象可以通过Class.forName()/getclass()/.class来获取,当jvm加载一个类时就会为这个类创建一个Class对象;

类的对象,通常就是指我们通过new这个类或者反射得到Class对象再调用newInstance()创建的对象,存在内存的堆中,也叫类的实例;

```java
public class MyService {
	synchronized public static void testMethod1() {
	
	}
	
	public void testMethod2() {
		synchronized(MyService.class) {
		
		}
	}
	
	synchronized public void testMethod3() {
		
	}
	
	public void testMethod4() {
		synchronized(this) {
		
		}
	}
	
	public void testMethod5() {
		synchronized("abc") {
		
		}
	}

}
```

上面的代码中出现了三种类型的锁对象:

1. testMethod1() 和 testMethod2() 持有的锁是同一个,即MyService.java对应Class类的对象.
2. testMethod3() 和 testMethod4()持有的锁是同一个,即MyService.java类的对象.
3. testMethod5() 持有的锁是字符串abc

说明testMethod1()和testMethod2()是同步关系, testMethod3()和testMethod4()是同步关系.

#### volatile关键字

在java中,关键字volatile在使用上具有以下特性:

1. 可见性: B线程能马上看到A线程更改的数据
2. 原子性:

#### 可见性测试

```java
public class PrintString {
	private boolean isContinuePrint = true;
	
	public boolean isContinuePrint() {
		return isContinuePrint;
	}
	
	public void setContinuePrint(boolean isContinuePrint) {
		this.isContinuePrint = isContinuePrint;
	}
	
	public void printStringMethod() throws InterruptedException {
		while (isContinuePrint) {
			System.out.println("run printStringMethod threadName=" + Thread.currentThread().getName());
			Thread.sleep(1000);
		}
	}

}

public class Run {
	public static void main(String[] args) throws InterruptedException {
		PrintString printString = new PrintString();
		printString.printStringMethod();
		System.out.println("我要停止它？ stopThread=" + Thread.currentThread().getName());
		printString.setContinuePrint(false);
	}

}

```

程序运行后根本停不下来，主要是因为 main线程一直在处理while()循环,导致程序不能继续执行后面的代码.

#### 使用多线程解决死循环

```java
public class PrintString implements Runnable{
	private boolean isContinuePrint = true;
	
	public boolean isContinuePrint() {
		return isContinuePrint;
	}
	
	public void setContinuePrint(boolean isContinuePrint) {
		this.isContinuePrint = isContinuePrint;
	}
	
	public void printStringMethod() throws InterruptedException {
		while (isContinuePrint) {
			System.out.println("run printStringMethod threadName=" + Thread.currentThread().getName());
			Thread.sleep(1000);
		}
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			printStringMethod();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


public class Run {
	public static void main(String[] args) throws InterruptedException {
		PrintString printString = new PrintString();
		new Thread(printString).start();
		System.out.println("我要停止它？ stopThread=" + Thread.currentThread().getName());
		printString.setContinuePrint(false);
	}

}

```



#### 使用volatile关键字解决多线程出现的死循环

```java
public class RunThread extends Thread{
	volatile private boolean isRunning = true;
	public boolean isRunning() {
		return isRunning;
	}
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		System.out.println("进入run了");
		while (isRunning) {
			
		}
		System.out.println("线程被停止了");
	}

}


public class Run {
	public static void main(String[] args) throws InterruptedException {
		/**
		PrintString printString = new PrintString();
		new Thread(printString).start();
		System.out.println("我要停止它？ stopThread=" + Thread.currentThread().getName());
		printString.setContinuePrint(false);
		*/
		RunThread runThread = new RunThread();
		runThread.start();
		Thread.sleep(1000);
		runThread.setRunning(false);
		System.out.println("已经赋值为false");
	}

}
```

#### synchronized代码块具有增加可见性的作用

关键字synchronized可以使用多个线程访问同一个资源具有同步性,而且具有使线程工作内存中的私有变量与公共内存中的变量同步的特性,即可见性



## 三.线程通信

线程是操作系统中独立的个体,但这些个体如果不经过特殊的处理是不能成为一个整体的,线程间的通信是使这些独立的个体成为整数的必用方案之一,可以说,线程间进行任务的处理过程进行有效把控与部署.

#### wait/notify机制

```java
import java.util.ArrayList;
import java.util.List;

public class MyList {
	volatile private List list = new ArrayList();
	public void add() {
		// TODO Auto-generated method stub
		list.add("mfq");

	}
	public int size() {
		return list.size();
	}

}

public class ThreadA extends Thread{
	private MyList list;
	
	public ThreadA(MyList list) {
		// TODO Auto-generated constructor stub
		this.list = list;
		}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		for (int i = 0; i < 10; i++) {
			list.add();
			System.out.println("添加了" + (i + 1) + "个元素");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

public class ThreadB extends Thread{
	private MyList list;
	
	public ThreadB(MyList list) {
		// TODO Auto-generated constructor stub
		this.list = list;
		}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			while (true) {
				Thread.sleep(2000);
				if (list.size() == 5) {
					System.out.println("==5了,线程b要退出了!");
					throw new InterruptedException();
				}
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}

public class Test {
	public static void main(String[] args) {
		MyList service = new MyList();
		ThreadA a = new ThreadA(service);
		a.setName("A");
		a.start();
		ThreadB b = new ThreadB(service);
		b.setName("B");
		b.start();
	}

}
```

虽然两个线程间实现了通信,但缺点是线程ThreadB.java不停的通过while语句轮询机制来查询一个条件,这样会浪费CPU资源.如果轮询的时间间隔很小,则更浪费CPU资源;如果轮询的时间间隔过大,则有可能取不到想要的数据.



#### wait/noitify机制

wait/notify机制:

厨师和服务员的交互发生在菜品的传递台上,在这期间需考虑一下几个问题.

1. 厨师做完一个菜的时间未定,所以厨师将菜品放到"菜品传递台"上的时间也未定.
2. 服务员取到菜的时间取决于厨师,所以服务员就有等待状态
3. 服务员如何取到菜呢,这取决于厨师,厨师将菜放在"菜品传递台"上,其实相当于一种通知,这时服务员才可以拿到菜并交给就餐者.

在这个过程中出现了wait/notify机制.

#### wait方法的基本使用

wait方法的作用是是当前线程暂停运行,并释放锁.

```java
public class Test1 {
	public static void main(String[] args) {
		String newString = new String("");
		try {
			newString.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

```

运行如下

```java
Exception in thread "main" java.lang.IllegalMonitorStateException
	at java.lang.Object.wait(Native Method)
	at java.lang.Object.wait(Object.java:502)
	at com.mfq.n3_001_004.Test1.main(Test1.java:7)
```

出现异常的原因是没有对象监视器，即没有锁.

```java
public class Test2 {
	public static void main(String[] args) {

		try {
			String newString = new String("");
			System.out.println("syn上面");
			synchronized (newString) {
				System.out.println("syn第一行");
				newString.wait();
				System.out.println("wait下面的代码");
			}
			System.out.println("syn下面的代码");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
```

线程不能永远的等待下去,那样程序就停滞不前,不能继续向下运行了,如何使呈wait状态的线程继续运行呢?答案就是使用notify()方法.

#### 完整实现wait/notify机制

```java
public class MyThread1 extends Thread{

	private Object lock;
	public MyThread1(Object lock) {
		// TODO Auto-generated constructor stub
		this.lock = lock;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		synchronized (lock) {
			System.out.println("start wait time=" + System.currentTimeMillis());
			try {
				lock.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("end wait time=" + System.currentTimeMillis());
		}
	}
}


public class MyThread2 extends Thread{

	private Object lock;
	public MyThread2(Object lock) {
		// TODO Auto-generated constructor stub
		this.lock = lock;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		synchronized (lock) {
			System.out.println("start notify time=" + System.currentTimeMillis());
			lock.notify();
			System.out.println("end notify time=" + System.currentTimeMillis());
		}
	}
}

public class Test {
	public static void main(String[] args) throws InterruptedException {
		Object lock = new Object();
		MyThread1 t1 = new MyThread1(lock);
		t1.start();
		Thread.sleep(3000);
		MyThread2 t2 = new MyThread2(lock);
		t2.start();
		
		
	}

}

```

运行如下

```java
start wait time=1575100711433
start notify time=1575100714432
end notify time=1575100714432
end wait time=1575100714432
```

从程序运行结果来看,3s后线程被通知(notify)唤醒.

#### 使用wait/notify机制实现list.size()等于5时的线程销毁

```java
public class MyList {
	private static List list = new ArrayList();

	public static void add() {
		list.add("anyString");
	}

	public static int size() {
		return list.size();
	}
}

public class MyThread1 extends Thread{

	private Object lock;
	public MyThread1(Object lock) {
		// TODO Auto-generated constructor stub
		this.lock = lock;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		synchronized (lock) {
			if (MyList.size() != 5) {
				System.out.println("start wait time=" + System.currentTimeMillis());
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("end wait time=" + System.currentTimeMillis());
			}
			
		}
	}
}

public class MyThread2 extends Thread{

	private Object lock;
	public MyThread2(Object lock) {
		// TODO Auto-generated constructor stub
		this.lock = lock;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			synchronized (lock) {
				for (int i = 0; i < 10; i++) {
					MyList.add();
					if (MyList.size() == 5) {
						lock.notify();
						System.out.println("已经发出通知!");
					}
					System.out.println("添加了" + (i + 1) + "个元素");
					Thread.sleep(1000);
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}


public class Run {
	public static void main(String[] args) {
		try {
			Object lock = new Object();
			MyThread1 t1 = new MyThread1(lock);
			t1.start();
			Thread.sleep(50);
			MyThread2 t2 = new MyThread2(lock);
			t2.start();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
/**
start wait time=1575102821683
添加了1个元素
添加了2个元素
添加了3个元素
添加了4个元素
已经发出通知!
添加了5个元素
添加了6个元素
添加了7个元素
添加了8个元素
添加了9个元素
添加了10个元素
end wait time=1575102831740
**/
```

日志信息wait end在最后输出,这说明notify()方法执行后并不立即释放锁.

关键字synchronized可以将任何一个Object对象作为锁来看待,而java为每一个Object都实现了wait()和notify()方法,他们必须用在被synchronized同步的Object临界区内.通过调用wait()方法可以使处于临界区内的线程进入等待状态,同时释放被同步对象的锁.

wait()方法可以使调用该方法的线程释放锁,然后从运行状态转换成wait状态,等待被唤醒.

notify()方法按照执行wait()方法的顺序唤醒等待同一个锁的"一个线程"使其进入可运行状态.即notify()方法仅通知"一个"线程.

notifyAll()方法执行后,会按照执行wait()方法相反的顺序依次唤醒全部的线程.