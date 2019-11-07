# 多线程的学习

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

   

