package model.statements;

import model.PrgState;
import model.expression.*;
import model.utils.*;
import exceptions.*;

public class ifStmt implements iStatement{
	private iExpression expression;
	private iStatement thenS;
	private iStatement elseS;
	
	public ifStmt() {}
	
	public ifStmt(iExpression expression, iStatement thenS, iStatement elseS)
	{
		this.expression = expression;
		this.thenS = thenS;
		this.elseS = elseS;
	}
	
	@Override
	public PrgState execute(PrgState state) throws DivisionByZero, InvalidOperator, VariableNotDefined 
	{
		iStack<iStatement> stack = state.getexeStack();
		iDictionary<String,Integer> symtable = state.getSymtable();
		iHeap<Integer, Integer> heap = state.getHeap();
		if(expression.eval(symtable, heap)!=0)
			stack.push(thenS);
		else
			stack.push(elseS);
		return null;
	}
	
	@Override
	public String toString()
	{
		return "IF(" + expression.toString() + ") THEN(" + thenS.toString() + ") ELSE(" + elseS.toString() + ")";
 	}
	
	public iExpression getExpression()
	{
		return this.expression;
	}
	
	public iStatement getThenStmt() 
	{
		return this.thenS;
	}
	
	public iStatement getElseStmt()
	{
		return this.elseS;
	}
}
