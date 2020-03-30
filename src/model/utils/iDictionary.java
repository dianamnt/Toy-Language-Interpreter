package model.utils;

import java.util.List;

import exceptions.VariableNotDefined;

public interface iDictionary<K,V> {
	boolean exists(K key);
	V getValue(K key) throws VariableNotDefined;
	List<V> getAllValues() throws VariableNotDefined;
	void setValue(K key, V value);
	Iterable<K> getAllKeys();
	void delete(K key);
	iDictionary<K,V> copy();
}
