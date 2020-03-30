package model.utils;

public interface iList<T> {
	void add(T obj);
	Iterable<T> getAll();
	void remove(T obj);
	T getLast();
}
