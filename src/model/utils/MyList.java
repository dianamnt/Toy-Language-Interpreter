package model.utils;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements iList<T>{

	private List<T> list = new ArrayList<>();
	
	@Override
	public void add(T obj) {
		
		list.add(obj);
	}

	@Override
	public Iterable<T> getAll() {
		return list;
	}
	
	@Override
	public T getLast()
	{
		return list.get(list.size()-1);
		
	}
	
	@Override
	public String toString()
	{
		StringBuilder build = new StringBuilder();
		for(T i: list)
			build.append(i).append(" ");
		
		return build.toString();
	}

	@Override
	public void remove(T obj) {
		list.remove(obj);
	}
	
}
