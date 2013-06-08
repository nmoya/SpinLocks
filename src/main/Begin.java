package main;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import assignment.*;


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
		Thread [] threadArray = new Thread[Global.nThreads];
		
		for(j=0; j < nTests; j++)
		{
			for (i = 0 ; i < Global.nThreads ; i ++) 
			{
				GenericThread tFilo = new GenericThread(i);
				threadArray[i] = new Thread(tFilo);
				threadArray[i].start();
			}
			for (i = 0 ; i < Global.nThreads ; i ++) 
			{
				threadArray[i].join();
			}
			System.out.println("Counter:" + Global.global_counter);
			Global.global_counter = 0;
		}
		
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