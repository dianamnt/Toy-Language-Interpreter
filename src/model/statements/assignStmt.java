package model.statements;

import model.PrgState;
import model.utils.*;
import exceptions.*;
import model.expression.*;

public class assignStmt implements iStatement{
	private String id;
	private iExpression expression;
	
	//public assignStmt() {}
	
	public assignStmt(String id, iExpression expression)
	{
		this.id = id;
		this.expression = expression;
	}
	
	@Override
	public PrgState execute(PrgState state) throws DivisionByZero, InvalidOperator, VariableNotDefined 
	{
		iDictionary<String,Integer> symtable = state.getSymtable();
		iHeap<Integer, Integer> heap = state.getHeap();
		int x = expression.eval(symtable,heap);
		if(symtable.exists(id))
			symtable.setValue(id, x);
		else
			symtable.setValue(id,x);
		return null;
	}
	
	@Override
	public String toString()
	{
		return id + "=" + expression.toString();
	}
	
	public String getId()
	{
		return this.id;
	}
	
	public iExpression getExpression()
	{
		return this.expression;
	}
}
