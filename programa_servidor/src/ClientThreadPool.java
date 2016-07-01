package programa_servidor;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientThreadPool {
/*
	private ArrayList<Thread> pool;
	private ArrayList<ClientWorker> workers;
	private ArrayList<Boolean> estadoThreads;

	public ClientThreadPool(int size, KeyValueStore store) {
		this.pool = new ArrayList<Thread>();
		this.workers = new ArrayList<ClientWorker>();
		this.estadoThreads = new ArrayList<Boolean>();

		for(int i=0; i<size; i++) {
			ClientWorker worker = new ClientWorker(store);
			this.workers.add(worker);
			this.pool.add(new Thread(worker));
			this.estadoThreads.add(new Boolean(true));
		}
	}

	public synchronized Thread getFirstFreeThread(Socket cliente) {
		int i = 0;
		for(Boolean b : this.estadoThreads) {
			//Si el estado es true entonces el thread esta libre
			if (Boolean.TRUE.equals(b)) {
				this.estadoThreads(i, new Boolean(false));
				Thread t = this.pool.get(i);
				ClientWorker w = this.workers.get(i);
				w.setCliente(cliente);
				return this.pool.get(i);
			}
			i++;
		}
		return null;
	}
*/
}
