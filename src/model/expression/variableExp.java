package model.expression;

import model.utils.iDictionary;
import model.utils.iHeap;
import exceptions.VariableNotDefined;

public class variableExp implements iExpression {
	String id;
	public variableExp(String id) {
		this.id = id;
	}
	
	@Override
	public int eval(iDictionary<String,Integer> d, iHeap<Integer,Integer> heap) throws VariableNotDefined
	{
		return d.getValue(id);
	}
	
	@Override
	public String toString() {
		return id;
	}
}
