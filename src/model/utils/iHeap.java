package model.utils;

import java.util.Map;
import java.util.Set;

public interface iHeap<K,V> {
	public void add(K key, V value);
	public boolean exists(K key);
	public void update(K key, V value);
	public void remove(K key);
	public V getValue(K key);
	public Iterable<K> getElems();
	public void setElems(Map<K, V> m);
	public Set<Map.Entry<K,V>> entrySet();
	public Map<K,V> getElemss();
}
