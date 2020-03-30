package model.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyLockTable<K,V> implements iLockTable<K,V> {
    private Map<K,V> dict = new HashMap<>();

    @Override
    public void add(K key, V value) {
        dict.put(key, value);
    }

    @Override
    public boolean exists(K key) {
        return dict.containsKey(key);
    }

    @Override
    public void update(K key, V value) {

        dict.put(key, value);
    }

    @Override
    public void remove(K key) {
        dict.remove(key);
    }

    @Override
    public V getValue(K key) {
        return dict.get(key);
    }

    @Override
    public Iterable<K> getElems() {
        return dict.keySet();
    }

    @Override
    public void setElems(Map<K, V> m) {
        dict = m;
    }

    public Map<K,V> getElemss()
    {
        return dict;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return dict.entrySet();
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for(Map.Entry<K, V> e : this.dict.entrySet()){
            str.append("<"+e.getKey() + ", " + e.getValue() + ">; ");
        }
        return str.toString();
    }
}
