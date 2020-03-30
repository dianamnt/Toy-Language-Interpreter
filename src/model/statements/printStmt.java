package model.statements;

import model.utils.*;
import model.expression.*;
import model.PrgState;
import exceptions.*;

public class printStmt implements iStatement{
	private iExpression expression;
	
	public printStmt() {}
	
	public printStmt(iExpression expression)
	{
		this.expression = expression;
	}
	
	@Override
	public PrgState execute(PrgState state) throws DivisionByZero, InvalidOperator, VariableNotDefined
	{
		iList<Integer> queue = state.getOutput();
		iDictionary<String,Integer> symtable = state.getSymtable();
		iHeap<Integer, Integer> heap = state.getHeap();
		queue.add(expression.eval(symtable, heap));
		return null;
	}
	
	@Override
	public String toString() {
		return "print(" + expression.toString() + ")";
 	}
	
	public iExpression getExpression()
	{
		return this.expression;
	}
}
