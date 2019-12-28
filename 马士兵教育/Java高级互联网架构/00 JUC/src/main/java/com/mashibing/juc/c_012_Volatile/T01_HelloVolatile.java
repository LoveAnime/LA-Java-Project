/**
 * volatile�����ã�
 * 1����֤һ�������ڶ���߳̿ɼ���MESI��CPU�Ļ���һ����Э��
 * 2����ָֹ��������˫�ؼ��ĵ���ģʽ
 *
 * A B�̶߳��õ�һ��������javaĬ����A�߳��б���һ��copy���������B�߳��޸��˸ñ�������A�߳�δ��֪��
 * ʹ��volatile�ؼ��֣����������̶߳�������������޸�ֵ
 * 
 * ������Ĵ����У�running�Ǵ����ڶ��ڴ��t������
 * ���߳�t1��ʼ���е�ʱ�򣬻��runningֵ���ڴ��ж���t1�̵߳Ĺ������������й�����ֱ��ʹ�����copy��������ÿ�ζ�ȥ
 * ��ȡ���ڴ棬�����������߳��޸�running��ֵ֮��t1�̸߳�֪���������Բ���ֹͣ����
 * 
 * ʹ��volatile������ǿ�������̶߳�ȥ���ڴ��ж�ȡrunning��ֵ
 * 
 * �����Ķ���ƪ���½��и���������
 * http://www.cnblogs.com/nexiyi/p/java_memory_model_and_thread.html
 *
 * volatile��synchronized�Ĺ�ϵ��
 * synchronized���ܱ�֤ԭ���ԣ���������ָֹ��������
 * volatile���ܱ�֤�ɼ��ԣ��������ܱ�֤ԭ���ԣ�������̹߳�ͬ�޸�running����ʱ�������Ĳ�һ������
 *
 * Ҳ����˵volatile�������synchronized��synchronizedҲ���ܴ���volatile������ʵ�ֵĹ��ܲ�һ����
 *
 * @author mashibing
 */
package com.mashibing.juc.c_012_Volatile;

import java.util.concurrent.TimeUnit;

public class T01_HelloVolatile {
	/*volatile*/ boolean running = true; //�Ա�һ������volatile������£������������н��������
	void m() {
		System.out.println("m start");
		while(running) {
		}
		System.out.println("m end!");
	}
	
	public static void main(String[] args) {
		T01_HelloVolatile t = new T01_HelloVolatile();
		
		new Thread(t::m, "t1").start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t.running = false;
	}
	
}


