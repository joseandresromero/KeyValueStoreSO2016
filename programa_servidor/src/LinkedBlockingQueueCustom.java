package programa_servidor;

import java.util.LinkedList;
import java.util.List;

class LinkedBlockingQueueCustom<E> implements BlockingQueueCustom<E>{
 
      private List<E> queue;
      private int  maxSize ; //Maximo numero de elementos de la cola
 
      public LinkedBlockingQueueCustom(int maxSize){
           this.maxSize = maxSize;
           queue = new LinkedList<E>();
      }
 
      public synchronized void put(E item)  throws InterruptedException  {
         
	//Se verifica si hay espacio disponible en la cola
	if (queue.size() == maxSize) {
           this.wait();
        }
        
        //Se agrega el elemento y se notifica a los demas threads que estan esperando.
        queue.add(item);
        this.notifyAll();
      }
 
      public synchronized E take()  throws InterruptedException{
 
        //Espera a que haya un elemento disponible para scar de la cola
        if (queue.size() == 0) {
           this.wait();
        }
 
        //Se quita el elemento de la cola y se notifica a los demas thread sque estan esperando.
        this.notifyAll();
        return queue.remove(0);           
      }
 
      public synchronized int size() {
              return queue.size();
      }
 
}
