package model.expression;

import exceptions.DivisionByZero;
import exceptions.InvalidOperator;
import exceptions.VariableNotDefined;
import model.utils.iDictionary;
import model.utils.iHeap;

public class booleanExp implements iExpression{
	private iExpression st;
	private iExpression dr;
	private String op;
	
	public booleanExp(iExpression st, iExpression dr, String op)
	{
		this.op = op;
		this.st = st;
		this.dr = dr;
	}
	
	@Override
	public int eval(iDictionary<String, Integer> d, iHeap<Integer,Integer> heap) throws DivisionByZero, InvalidOperator, VariableNotDefined
	{
		int vst = st.eval(d, heap);
		int vdr = dr.eval(d, heap);
		
		if(op == "=")
		{
			if(vst == vdr)
				return 1;
			return 0;
		}
		
		if(op == "!=")
		{
			if(vst != vdr)
				return 1;
			return 0;
		}
		
		if(op == "<=")
		{
			if(vst <= vdr)
				return 1;
			return 0;
		}
		
		if(op == "<")
		{
			if(vst < vdr)
				return 1;
			return 0;
		}
		
		if(op == ">=")
		{
			if(vst >= vdr)
				return 1;
			return 0;
		}
		
		if(op == ">")
		{
			if(vst > vdr)
				return 1;
			return 0;
		}
	
		throw new InvalidOperator("The only allowed operators are: =, !=, <, <=, >, >=.");	
	}
	
	@Override
	public String toString() {
		return "" + st + op + dr;
	}
}
