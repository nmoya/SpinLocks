package main;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import assignment.*;
/*
 * 
 * whether any thread is holding a lock (but does not actually acquire the lock)
 * 
 */

class Begin
{
	public static void main(String args[]) throws IOException, InterruptedException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));  
		String input = null;
		int i = 0;
		int j=0;
		System.out.println("Digite o numero de threads (sugestao 2): ");
		input = in.readLine();
		Global.nThreads = Integer.valueOf(input);
		Global.threadArray = new Thread[Global.nThreads];
		Thread WATCHER = new Thread();
		
		String[] LockMethod = new String[3];
		LockMethod = "TAS CLH MCS".split(" ");
		
		System.out.println("Number of Threads: " + Global.nThreads);
		System.out.println("Each thread will acquire the lock and sleep for 50ms.");
		System.out.println("The watcher should report something like this: free, ..., free, locked, ..., locked, free, ..., free");
		System.out.println("");
		
		for (j = 0; j < LockMethod.length; j++)	//For each one of the lock methods
		{
			System.out.println("Testing: " + LockMethod[j]);
			//Launch (nThreads + 1). nThreads acquiring the lock and 1 thread testing if the lock is being used.
			TestIsLocked tWatcher = new TestIsLocked(LockMethod[j]);
			WATCHER =  new Thread(tWatcher);
			for (i = 0 ; i < Global.nThreads ; i ++) 
			{
				GenericThread t = new GenericThread(i, LockMethod[j]);
				Global.threadArray[i] = new Thread(t);
				Global.threadArray[i].start();
			}
			WATCHER.start();
			//Wait until all threads have finished until start another cycle.
			WATCHER.join();
			System.out.println("Pressione qualquer tecla para continuar...");
			input = in.readLine();

		}
		System.out.println("FINISHED");
		



	}
}