package model.expression;

import model.utils.iDictionary;
import model.utils.iHeap;

public class constantExp implements iExpression{
	private int number;
	
	public constantExp(int nr) {
		this.number = nr;
	}
	
	@Override
	public int eval(iDictionary<String,Integer> d, iHeap<Integer, Integer> heap)
	{
		return number;
	}
	
	@Override
	public String toString() {
		return "" + number;
	}
}
