package assignment;

//import java.util.Random;
//import java.util.concurrent.Semaphore;

import main.Global;


public class TestIsLocked implements Runnable 
{
	private int id;	
	private String lockMethod;
	public TestIsLocked(String lockMethod) 
	{
		this.id = 0;
		this.lockMethod = lockMethod;
	}
	public int getID() { return this.id; }
	public void run() 
	{
		int i;
		int activeThreads = 0;
		try {

			while(true)
			{
				//Check if the following mechanism is locked.
				if (this.lockMethod.equals("TAS"))
				{
					if (Global.TASBarrier.isLocked())
						System.out.println("There is a thread holding " + this.lockMethod + " Lock");
					else
						System.out.println(this.lockMethod + " Lock is free");
				}
				else if (this.lockMethod.equals("CLH"))
				{
					if (Global.CLHBarrier.isLocked())
						System.out.println("There is a thread holding " + this.lockMethod + " Lock");
					else
						System.out.println(this.lockMethod + " Lock is free");
				}
				else if (this.lockMethod.equals("MCS"))
				{
					if (Global.MCSBarrier.isLocked())
						System.out.println("There is a thread holding " + this.lockMethod + " Lock");
					else
						System.out.println(this.lockMethod + " Lock is free");
				}

				
				//Wait until all threads have finished.
				activeThreads = 0;
				for (i=0; i < Global.nThreads; i++)
				{
					if (Global.threadArray[i].isAlive())
						activeThreads++;
				}
				if (activeThreads == 0)
					break;
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

