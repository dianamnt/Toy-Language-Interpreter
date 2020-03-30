package model;

import java.util.Map;

import exceptions.DivisionByZero;
import exceptions.FileExceptions;
import exceptions.InvalidOperator;
import exceptions.VariableNotDefined;
import model.statements.iStatement;
import model.utils.*;
//import model.utils.iDictionary;
//import model.utils.iList;
//import model.utils.iStack;

public class PrgState {
	private iStack<iStatement> exestack;
	private iDictionary<String, Integer> symtable;
	private iList<Integer> output;
	private iFileTable<Integer,FileData> fileTable;
	private iHeap<Integer,Integer> garbageCollector;
	private iLockTable<Integer,Integer> lockTable;
	private iStatement originalProgram;
	private int ID;
	
	PrgState() {}
	
	public PrgState(iStack<iStatement> exestack, iDictionary<String,Integer> symtable, iList<Integer> output, iStatement program, iFileTable<Integer,FileData> fileTable, iHeap<Integer,Integer> garbageCollector, int ID, iLockTable<Integer,Integer> lockTable)
	{
		this.exestack = exestack;
		this.symtable = symtable;
		this.output = output;
		this.originalProgram = program;
		this.fileTable = fileTable;
		this.garbageCollector = garbageCollector;
		this.ID = ID;
		this.lockTable = lockTable;
		//exestack.push(program);
	}
	
	public int getID()
	{
		return this.ID;
	}
	
	public void setID(int x)
	{
		this.ID = x;
	}
	
	public iStack<iStatement> getexeStack()
	{
		return this.exestack;
	}
	
	public void setexeStack(iStack<iStatement> exestack)
	{
		this.exestack = exestack;
	}
	
	public iDictionary<String,Integer> getSymtable()
	{
		return this.symtable;
	}
	
	public void setSymtable(iDictionary<String, Integer> symtable)
	{
		this.symtable = symtable;
	}
	
	public void setOutput(iList<Integer> output)
	{
		this.output = output;
	}
	
	public iList<Integer> getOutput()
	{
		return this.output;
	}
	
	public iStatement getOriginalProgram()
	{
		return this.originalProgram;
	}
	
	public void setOriginalProgram(iStatement originalProgram)
	{
		this.originalProgram = originalProgram;
	}
	
	public String toString()
	{
		return ID + ". \n" + "The execution stack is: " + exestack.toString() + "\n" + "The symblol table is: " + symtable.toString() + "\n" + "Output: " + output.toString() + "\n" + "Garbagge Collector:" + garbageCollector.toString() + "Lock table:" + lockTable.toString();
 	
	}
	
	public iHeap<Integer, Integer> getHeap()
	{
		return this.garbageCollector;
	}
	
	public void setHeap(iHeap<Integer,Integer> gc)
	{
		this.garbageCollector = gc;
	}
	
	public String toStringg()
	{
		return exestack.toString();
	}
	
	public iFileTable<Integer,FileData> getFileTable()
	{
		return this.fileTable;
	}
	
	public void setFileTable(iFileTable<Integer,FileData> ft)
	{
		this.fileTable = ft;
	}
	
	public boolean isNotCompleted(){
        return !exestack.isEmpty();
    }
	
	public PrgState oneStep() throws DivisionByZero, InvalidOperator, VariableNotDefined, FileExceptions{

        if(exestack.isEmpty())
            return null;
        iStatement stmt = exestack.pop();

        return stmt.execute(this);
    }


	public iLockTable<Integer, Integer> getLockTable() {
		return lockTable;
	}

	public void setLockTable(iLockTable<Integer, Integer> lockTable) {
		this.lockTable = lockTable;
	}
}
