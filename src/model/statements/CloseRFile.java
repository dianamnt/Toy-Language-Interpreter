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
import model.statements.iStatement;
import model.utils.iDictionary;
import model.utils.iHeap;

public class CloseRFile implements iStatement{
	private iExpression exp;
	
	public CloseRFile(iExpression exp)
	{
		this.exp = exp;
	}
	
	@Override
	public PrgState execute(PrgState state) throws DivisionByZero, InvalidOperator, VariableNotDefined, FileExceptions {

        iDictionary<String, Integer> dict = state.getSymtable();
        iFileTable<Integer, FileData> fileTable = state.getFileTable();
        iHeap<Integer, Integer> heap = state.getHeap();
        int id = exp.eval(dict,heap);


        if(!fileTable.contains(id))
            throw new FileExceptions("Can't close the file because it doesn't exist in FileTable");

        FileData fileData = fileTable.get(id);
        BufferedReader bufferedReader = fileData.getReader();

        try {
            bufferedReader.close();
        } catch (IOException e) {
            throw new FileExceptions("Can't close the file");
        }

        fileTable.delete(id);

        return null;
    }
	
	@Override
	public String toString(){
        return "close("+exp+")";
    }
	
}
