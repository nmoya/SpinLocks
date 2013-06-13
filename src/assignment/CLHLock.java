/*
 * CLHLock.java
 *
 * Created on January 20, 2006, 11:35 PM
 *
 * From "Multiprocessor Synchronization and Concurrent Data Structures",
 * by Maurice Herlihy and Nir Shavit.
 * Copyright 2006 Elsevier Inc. All rights reserved.
 */

package assignment; 

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.lang.ThreadLocal;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Craig-Hagersten-Landin Lock
 * @author Maurice Herlihy
 */
public class CLHLock implements Lock {
  // most recent lock holder
  AtomicReference<QNode> tail;
  
  // thread-local variables
  ThreadLocal<QNode> myNode, myPred;
  
  /**
   * Constructor
   */
  public CLHLock() {
    tail = new AtomicReference<QNode>(new QNode());
    // initialize thread-local variables
    myNode = new ThreadLocal<QNode>() {
      protected QNode initialValue() {
        return new QNode();
      }
    };
    myPred = new ThreadLocal<QNode>() {
      protected QNode initialValue() {
        return null;
      }
    };
  }
  
  //Cria um nó para mim e marca locked=true. Mostrando o meu desejo de entrar na RC
  //Pega o último da fila com tail.getAndSet(qnode);
  //Você entra atrás deste ultimo.
  //Enquanto o cara que está na sua frente nao terminou de usar, espere.
  //A fila e virtualizada. Cada um só olha para a frente.
  public void lock() {
    QNode qnode = myNode.get(); // use my node
    qnode.locked = true;        // announce start
    // Make me the new tail, and find my predecessor
    QNode pred = tail.getAndSet(qnode);
    myPred.set(pred);           // remember predecessor
    while (pred.locked) {System.out.print("");}      // spin
  }
  public void unlock() {
    QNode qnode = myNode.get(); // use my node
    qnode.locked = false;       // announce finish
    myNode.set(myPred.get());   // reuse predecessor
  }
  public boolean isLocked()
  {
	  //If the queue is empty, no one is holding the lock.
	  if(tail.get() == null)
		  return false;
	  //If there is someone on the queue
	  else
	  {
		  //If the last element is locked. Then himself is in the CS or waiting for some of his predecessor to leave.
		  QNode current = tail.get();
		  if (current.locked == true)
			  return true;
		  //If he is not in the CS nor waiting, then none is holding the lock.
		  else
			  return false;
	  }
  }
  
  
  
  
  
  
  
  
  
  
  
  // any class that implements lock must provide these methods
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
  
  static class QNode {  // Queue node inner class
    public boolean locked = false;
  }
}


