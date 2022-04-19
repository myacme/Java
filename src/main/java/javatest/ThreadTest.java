package javatest; /**
 * Copyright (C), 2015-2020, XXX有限公司
 * <p>
 * FileName: java.ThreadTest
 * <p>
 * Author:   MyAcme
 * <p>
 * Date:     2020/8/17 13:38
 * <p>
 * Description:
 * <p>
 * History:
 *
 * <author>          <time>          <version>          <desc>
 * <p>
 * 作者姓名           修改时间           版本号              描述
 */


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author MyAcme
 * @create 2020/8/17
 * @since 1.0.0
 */

public class ThreadTest {
	private int data;
	static int anInt = 0;
	int result = 0;

	public void m() {
		anInt = 1;
		result += 2;
		data += 2;
		System.out.println(result + " " + data);
	}

	static void ab() {
	}
}

class ThreadOfRun implements Runnable {
	private final String name;

	public ThreadOfRun(String name, int anInt) {
		this.name = name;
		this.anInt = anInt;
	}

	private int anInt;

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			anInt++;
			System.out.println(name + ": " + anInt);
		}
	}
}

class ThreadExample extends Thread {
	private String name;

	public ThreadExample(String name, int anInt) {
		this.name = name;
		this.anInt = anInt;
	}

	private int anInt;

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			anInt++;
			System.out.println(name + ": " + anInt);
			try {
			} catch (Exception e) {
			}
		}
	}

	public static void main(String[] args) {
		ThreadOfRun t = new ThreadOfRun("线程", 0);
		Thread t1 = new Thread(t, "线程一");
		Thread t2 = new Thread(t, "线程二");
		t1.start();
		t2.start();
	}
}

class Singleton {
	private static Singleton singleton = null;

	Singleton() {
	}

	public static Singleton newinstance() {
		if (singleton == null) {
			synchronized (Singleton.class) {
				System.out.println("获取锁");
				if (singleton == null) {
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}
}



class ThreadDemo extends Thread {
	ThreadDemo demo ;
	private String name;
	public ThreadDemo(String name){
		this.name = name;
	}
	public ThreadDemo(ThreadDemo name){
		this.demo = name;
	}
	static int anInt = 0;
	Lock l = new ReentrantLock();

	public synchronized void count(String s) {
		synchronized (ThreadDemo.class) {
			for (int i = 0; i < 10; i++) {
				System.out.println(s + ":" + ++anInt);
			}
		}
	}

	public synchronized void count1(String s) {
		for (int i = 0; i < 10; i++) {
			System.out.println(s + ":" + ++anInt);
		}
	}

	public synchronized void count2(String s) {
		l.lock();
		try {

			for (int i = 0; i < 10; i++) {
				System.out.println(s + ":" + ++anInt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			l.unlock();
		}
	}

	public synchronized void count3(String s) {
		l.lock();
		try {

			for (int i = 0; i < 10; i++) {
				System.out.println(s + ":" + ++anInt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			l.unlock();
		}
	}


	@Override
	public void  run(){
		synchronized(ThreadDemo.class){
			System.out.println(demo.name);
			try {
				Thread.sleep(1000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	public static void main(String[] args){
		ThreadDemo threadDemo = new ThreadDemo(new ThreadDemo("a"));
		ThreadDemo threadDemo1 = new ThreadDemo(new ThreadDemo("b"));
		threadDemo.start();
		threadDemo1.start();

	}
}

