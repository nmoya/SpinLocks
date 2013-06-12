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
		String numeroThreads = null;
		int i = 0;
		int nTests = 10, j=0;
		Global.nThreads = Integer.valueOf(5);
		Global.threadArray = new Thread[Global.nThreads];
		Thread WATCHER = new Thread();
		
		String[] LockMethod = new String[3];
		LockMethod = "TAS MCS CLH".split(" ");
		
		System.out.println("Number of Threads: " + Global.nThreads);
		System.out.println("Each thread will increment a global counter: " + Global.iteracoes);
		System.out.println("By the end, the counter must be: " + Global.iteracoes * Global.nThreads);
		System.out.println("");
		
		for (j = 0; j < LockMethod.length; j++)	//For each one of the lock methods
		{
			if (j != 2)
				continue;
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
			System.out.println("");

		}
		System.out.println("FINISHED");
		
		
		
		
		
		
		
		
		
		/*
		
		
		
		//Test the correctness of the lock mechanism after the alteration
		boolean incorrect = false;
		Global.global_counter = 0;
		for(j=0; j < nTests; j++)
		{
			for (i = 0 ; i < Global.nThreads ; i ++) 
			{
				GenericThread tGeneric = new GenericThread(i, "MCS");
				Global.threadArray[i] = new Thread(tGeneric);
				Global.threadArray[i].start();
			}
			//Wait until all threads have finished until start another test cycle.
			for (i = 0 ; i < Global.nThreads ; i ++)  Global.threadArray[i].join();
			if ((Global.iteracoes * Global.nThreads) != Global.global_counter)
			{
				incorrect = true;
				break;
			}
			//System.out.println(j);
			Global.global_counter = 0;
		}
		if (!incorrect)
			System.out.println("Succeded!");
		else
			System.out.println("Failed!");
		*/
		
		/*System.out.println("1- [SOLUCAO OFICIAL] Algoritmo do saleiro. N Threads");
		System.out.println("2- Sem Deadlock e com Inanicao para N threads. Usando Semaforos JAVA");
		System.out.println("3- Com DeadLock para N threads");
		System.out.println("Digite o algoritmo a ser executado: ");
		input = in.readLine();
		System.out.println("Voce digitou: " + input);

		System.out.println("Digite o Numero de Threds: ");
		numeroThreads = in.readLine();
					


		
		//[SOLUCAO OFICIAL] Algoritmo do Saleiro.
		if (entrada.equals("1"))
		{}
			*/
			


	}
}