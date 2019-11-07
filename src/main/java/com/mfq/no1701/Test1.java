package com.mfq.no1701;

public class Test1 {
	public void a() {
		b();
	}

	public void b() {
		// TODO Auto-generated method stub
		c();
	}

	public void c() {
		// TODO Auto-generated method stub
		d();
	}

	public void d() {
		// TODO Auto-generated method stub
		e();
	}

	public void e() {
		// TODO Auto-generated method stub
		StackTraceElement[] array = Thread.currentThread().getStackTrace();
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				StackTraceElement eachElement = array[i];
				System.out.println("className=" + eachElement.getClassName() + " methodName="
						+ eachElement.getMethodName() + " fileName=" + eachElement.getFileName()
						+ " lineNumber=" + eachElement.getLineNumber());
			}
			
		}
		
	}
	public static void main(String[] args) {
		Test1 test1 = new Test1();
		test1.a();
	}
}
