package assignment;

//import java.util.Random;
//import java.util.concurrent.Semaphore;

import main.Global;


public class GenericThread implements Runnable 
{
	private int id;	
	public GenericThread(int id) 
	{
		this.id = id;
	}
	public int getID() { return this.id; }
	public void run() 
	{
		//Random randint = new Random(42);
		int count = 0;
		//this.info("iniciado");
		try {
			
			
			while(count < Global.iteracoes)
			{

				//Global.sem.acquire();
				//Global.CLHBarrier.lock();
				//Global.MCSBarrier.lock();
				Global.TTASBarrier.lock();
				Global.global_counter++;
				Global.TTASBarrier.unlock();
				//Global.MCSBarrier.unlock();
				//Global.CLHBarrier.unlock();
				//Global.sem.release();
				count++;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		//this.info("FIM");
	}
	public void info (String s)
	{
		System.out.println("Thread " + this.getID() + ": " + s);
	}
}