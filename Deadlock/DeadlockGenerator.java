package com.sapient.ace.training.day9.GC.deadlock;

public class DeadlockGenerator {

	public static void main(String[] args) throws InterruptedException {
		//DeadlockGenerator gen = new DeadlockGenerator(new Object(), new Object());
		Object res1 = new Object();
		Object res2 = new Object();
		
		Thread t1 = new Thread(new DeadLockThread1(res1, res2));
		Thread t2 = new Thread(new DeadLockThread2(res1, res2));
		t1.start();
		t2.start();
		t1.join();t2.join();		
	}
}

class DeadLockThread1 implements Runnable{
	
	private Object resource1;
	private Object resource2;	

	public DeadLockThread1(Object resource1, Object resource2) {
		super();
		this.resource1 = resource1;
		this.resource2 = resource2;
	}

	@Override
	public void run() {
		
		synchronized (resource1) {
			System.out.println("Thread1 holding resource 1");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			synchronized (resource2) {
				System.out.println("Thread1 holding resource 2");				
			}			
		}		
	}	
}

class DeadLockThread2 implements Runnable{
	
	private Object resource1;
	private Object resource2;	

	public DeadLockThread2(Object resource1, Object resource2) {
		super();
		this.resource1 = resource1;
		this.resource2 = resource2;
	}

	@Override
	public void run() {
		
		synchronized (resource2) {
			System.out.println("Thread2 holding resource 2");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			synchronized (resource1) {
				System.out.println("Thread2 holding resource 1");
				
			}			
		}		
	}	
}
