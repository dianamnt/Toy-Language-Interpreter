package model.utils;

import java.util.ArrayList;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.VariableNotDefined;

public class MyDictionary<K,V> implements iDictionary<K,V> {

	private Map<K,V> dict = new HashMap<>(); 
	
	@Override
	public boolean exists(K key) {
		return dict.containsKey(key);
	}

	@Override
	public V getValue(K key) throws VariableNotDefined {
		if(!exists(key))
			throw new VariableNotDefined("Variable not defined in this dictionary");
		return dict.get(key);
	}

	@Override
	public List<V> getAllValues() throws VariableNotDefined {
		List<V> list = new ArrayList<>();
		
		for(K key: this.getAllKeys())
			list.add(this.getValue(key));
		return list;
	}

	@Override
	public void setValue(K key, V value) {
		dict.put(key, value);
		
	}

	@Override
	public Iterable<K> getAllKeys() {
		return dict.keySet();
	}

	@Override
	public void delete(K key) {
		dict.remove(key);
		
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for(Map.Entry<K, V> e: this.dict.entrySet())
		{
			str.append("<" + e.getKey() + "," + e.getValue() + ">; ");
		
		}
		return str.toString();
	}

	@Override
	public iDictionary<K, V> copy() {
		iDictionary<K,V> symTable = new MyDictionary<>();
		for(Map.Entry<K, V> e: this.dict.entrySet())
		{
			symTable.setValue(e.getKey(), e.getValue());
		}
		return symTable;
	}

}
