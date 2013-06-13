package assignment;

//import java.util.Random;
//import java.util.concurrent.Semaphore;

import main.Global;


public class GenericThread implements Runnable 
{
	private int id;	
	private String lockMethod;
	public GenericThread(int id, String lockMethod) 
	{
		this.id = id;
		this.lockMethod = lockMethod;
	}
	public int getID() { return this.id; }
	public void run() 
	{
		

		//Random randint = new Random(42);
		int count = 0;
		//this.info("iniciado");
		try {
			Thread.sleep(500);	//Wait until WATCHER thread starts
			while(count < Global.iteracoes)
			{
				try{
					//Test which lock will be used before entering the CS.
					if (this.lockMethod.equals("CLH"))
						Global.CLHBarrier.lock();
					else if (this.lockMethod.equals("MCS"))
						Global.MCSBarrier.lock();
					else if (this.lockMethod.equals("TTAS"))
						Global.TTASBarrier.lock();
					else if (this.lockMethod.equals("TAS"))
						Global.TASBarrier.lock();
					else if (this.lockMethod.equals("SEM"))
						Global.sem.acquire();

					//CS
					this.info("Acquired the Lock");
					Thread.sleep(50);
					this.info("Released the Unlock");
				} 
				finally { 
					//Leaving the critical session with the same lock mechanism.
					if (this.lockMethod.equals("CLH"))
						Global.CLHBarrier.unlock();
					else if (this.lockMethod.equals("MCS"))
						Global.MCSBarrier.unlock();
					else if (this.lockMethod.equals("TTAS"))
						Global.TTASBarrier.unlock();
					else if (this.lockMethod.equals("TAS"))
						Global.TASBarrier.unlock();
					else if (this.lockMethod.equals("SEM"))
						Global.sem.release();
				}

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

