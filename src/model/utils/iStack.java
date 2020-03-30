package model.utils;

public interface iStack<T> {
	void push(T obj);
	T pop();
	boolean isEmpty();
	T top();
	Iterable<T> getAll();
}
