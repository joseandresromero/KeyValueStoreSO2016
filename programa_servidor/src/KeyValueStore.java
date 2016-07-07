package programa_servidor;

import java.io.*;
import java.net.*;
import java.util.*;

public class KeyValueStore {

	private Map<String,String> store;

	public KeyValueStore() {
		this.store = new HashMap<String,String>();
	}

	public synchronized void set(String key, String value) {
		store.put(key, value);
	}

	public synchronized String get(String key) {
		return store.get(key);
	}

	public synchronized Set<String> list() {
		return store.keySet();
	}

	public synchronized void del(String key) {
                store.remove(key);
        }

}
