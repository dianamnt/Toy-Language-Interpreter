package model.expression;

import exceptions.DivisionByZero;
import exceptions.InvalidOperator;
import exceptions.VariableNotDefined;
import model.utils.iDictionary;
import model.utils.iHeap;

public class arithmeticExp implements iExpression{
	private iExpression st, dr;
	private char op;
	
	public arithmeticExp(char op, iExpression st, iExpression dr )
	{
		this.op = op;
		this.st = st;
		this.dr = dr;
	}
	
	@Override
	public int eval(iDictionary<String,Integer> dict, iHeap<Integer, Integer> heap) throws DivisionByZero, InvalidOperator, VariableNotDefined
	{
		int vst = st.eval(dict, heap);
		int vdr = dr.eval(dict, heap);
		
		switch(op) {
		case '+':
			return vst + vdr;
		case '-':
			return vst - vdr;
		case '*':
			return vst * vdr;
		case '/':
			if(vdr == 0)
				throw new DivisionByZero("Division by zero is not allowed");
			return vst/vdr;
		}
		
		throw new InvalidOperator("The only allowed operators are: +, -, *, /.");
	}
	
	@Override
	public String toString() {
		return "" + st + op + dr;
	}
}
