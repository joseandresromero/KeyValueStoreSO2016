package programa_servidor;

import java.util.LinkedList;
import java.util.List;
 
public interface BlockingQueueCustom<E> {
 
      void put(E item)  throws InterruptedException ;
 
      E take()  throws InterruptedException;
      
      int size();
 
}
