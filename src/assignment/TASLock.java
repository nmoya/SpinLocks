package assignment;

/**
 * Test-and-set lock
 * @author Nikolas Moya
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class TASLock implements Lock {
	/*
	 * State variable controls the state of the critical session.
	 * If the state is false, then no thread is currently in the CS.
	 * If the state is true, then a thread must spin until the thread currently using unlocks.
	 * isLocked () method, simply test the state variable. 
	 * */
  AtomicBoolean state = new AtomicBoolean(false);
  public void lock() 
  {
      while (state.getAndSet(true)) {}
  }
  public void unlock() {
    state.set(false);
  }
  public boolean isLocked ()
  {
	  //If state is TRUE, then a thread is holding the lock.
	  //False, otherwise
	  return state.get();
  }
  
  
  
  
  
  // Below here:  
  // @author Maurice Herlihy
  // Any class that implents Lock must provide these methods.
  public Condition newCondition() {
    throw new java.lang.UnsupportedOperationException();
  }
  public boolean tryLock(long time,
      TimeUnit unit)
      throws InterruptedException {
    throw new java.lang.UnsupportedOperationException();
  }
  public boolean tryLock() {
    throw new java.lang.UnsupportedOperationException();
  }
  public void lockInterruptibly() throws InterruptedException {
    throw new java.lang.UnsupportedOperationException();
  }
}

