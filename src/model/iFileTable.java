package model;

import java.util.Collection;

public interface iFileTable<K,V> {
	public void add(K key, V val);
	public void delete(K key);
	public V get(K key);
	public boolean contains(K key);
	public Iterable<K> getAllKeys();
	public Collection<V> getAllValues();
}
