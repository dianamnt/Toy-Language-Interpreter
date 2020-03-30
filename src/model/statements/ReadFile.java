package model.statements;

import java.io.BufferedReader;
import java.io.IOException;

import exceptions.DivisionByZero;
import exceptions.FileExceptions;
import exceptions.InvalidOperator;
import exceptions.VariableNotDefined;
import model.FileData;
import model.PrgState;
import model.iFileTable;
import model.expression.iExpression;
import model.utils.iDictionary;
import model.utils.iHeap;

public class ReadFile implements iStatement{
	private iExpression exp;
	private String varName;
	
	public ReadFile(iExpression exp, String vn)
	{
		this.exp = exp;
		this.varName = vn;
	}
	
	@Override
	public PrgState execute(PrgState state) throws FileExceptions, DivisionByZero, InvalidOperator, VariableNotDefined {

        iDictionary<String, Integer> dict = state.getSymtable();
        iFileTable<Integer, FileData> fileTable = state.getFileTable();
        iHeap<Integer, Integer> heap = state.getHeap();
        int id = exp.eval(dict, heap);

        if(!fileTable.contains(id))
            throw new FileExceptions("This ID is not in FileTable");

        BufferedReader reader = fileTable.get(id).getReader();

        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new FileExceptions("Can't read line");
        }

        int value = 0;
        if(line != null)
            value = Integer.parseInt(line);

        dict.setValue(varName, value);

        return null;
    }
	
	@Override
	public String toString(){
        return "read("+varName + ", " + exp+")";
    }
}
