package model.utils;

import java.util.ArrayDeque;
import java.util.Deque;

public class MyStack<T> implements iStack<T> {
	
	private Deque<T> stack = new ArrayDeque<>();

	@Override
	public void push(T obj) {
		stack.push(obj);
	}

	@Override
	public T pop() {
		return stack.pop();
	}

	@Override
	public boolean isEmpty() {
		return stack.size() == 0; 
	}

	@Override
	public T top() {
		
		return stack.peek();
	}

	@Override
	public Iterable<T> getAll() {
		return stack;
	}
	
	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		for(T i : stack) {
			str.append(i);
		}
		return str.toString();
	}
	
}
