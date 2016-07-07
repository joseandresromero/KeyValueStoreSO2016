package programa_servidor;

import java.util.LinkedList;
import java.util.List;

class ThreadPoolsThread extends Thread {
 
    private BlockingQueueCustom<Runnable> taskQueue;
    private ThreadPool threadPool;
 
    public ThreadPoolsThread(BlockingQueueCustom<Runnable> queue,
                  ThreadPool threadPool){
        taskQueue = queue;
        this.threadPool=threadPool;
       
    }
 
    public void run() {
           try {
                  while (true) {    
//                        System.out.println(Thread.currentThread().getName()+" is READY to execute task.");

			//El thread toma un task de la cola compartida para ejecutarla, si no hay tasks espera a que haya alguna disponible
                        Runnable runnable = taskQueue.take();
//                        System.out.println(Thread.currentThread().getName()+" has taken task.");
                        //Se ejecuta el task
                        runnable.run();                
                        
//                        System.out.println(Thread.currentThread().getName()+" has EXECUTED task.");

			//Si se inicio el el shutdown del pool y ya no quedan task que ejecutar, se interrumpe el thread
                        if(this.threadPool.isPoolShutDownInitiated()
                                      &&  this.taskQueue.size()==0){
                               this.interrupt();
                               Thread.sleep(1);  
                        }   
                  }
           } catch (Exception e) {
//                  System.out.println(Thread.currentThread().getName()+" has been STOPPED.");
           }
    }
}
