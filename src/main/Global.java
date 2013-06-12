package main;

import java.util.concurrent.Semaphore;


import assignment.*;

public class Global {
	
	public static int nThreads = 5;
	public static int aceleradorInvertido = 0;
	public static int iteracoes = 100;
	public static int global_counter = 0;

	public static Thread [] threadArray;
	public static Semaphore sem = new Semaphore(1);
	
	public static CLHLock CLHBarrier = new CLHLock();
	public static MCSLock MCSBarrier = new MCSLock();
	public static TTASLock TTASBarrier = new TTASLock();
	public static TASLock TASBarrier = new TASLock();

}
