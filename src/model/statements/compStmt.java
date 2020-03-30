package model.statements;

import model.PrgState;

import model.utils.*;
import exceptions.*;

public class compStmt implements iStatement{
	private iStatement first;
	private iStatement second;
	
	public compStmt() {}
	
	public compStmt(iStatement first, iStatement second)
	{
		this.first = first;
		this.second = second;
	}
	
	@Override
	public PrgState execute(PrgState state)
	{
		iStack<iStatement> stack = state.getexeStack();
		stack.push(second);
		stack.push(first);
		return null;
	}
	
	@Override
	public String toString()
	{
		return "(" + first.toString() + ";" + second.toString() + ")";
 	}
	
	public iStatement getFirst()
	{
		return this.first;
	}
	
	public iStatement getSecond()
	{
		return this.second;
	}
}
