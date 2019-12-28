/**
 * 分析一下这个程序的输出
 * @author mashibing
 */

package com.mashibing.juc.c_005;

public class T implements Runnable {

	private /*volatile*/ int count = 100;

	/**
	 * 这个程序，如果只加volatile关键字并不能保证输出是对的
	 * 因为volatile只能保证可见性，不能保证原子性
	 * 即count--一旦执行就立马同步到其他线程
	 * 当前线程的system.count可能已经被其他线程的count--了
	 * 只能加synchronized
	 */
	public /*synchronized*/ void run() { 
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}
	
	public static void main(String[] args) {
		T t = new T();
		for(int i=0; i<100; i++) {
			new Thread(t, "THREAD" + i).start();
		}
	}
	
}
