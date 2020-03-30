package repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import exceptions.FileExceptions;
import exceptions.VariableNotDefined;

import java.io.*;

import model.FileData;
import model.PrgState;
import model.iFileTable;
import model.statements.iStatement;
import model.utils.*;

public class Repository implements iRepository{
	private List<PrgState> elems;
	private String logFilePath; 
	
	public Repository(String lfp)
	{
		this.elems = new ArrayList<PrgState>();
		this.logFilePath = lfp;
	}
	
	@Override
	public void addPrg(PrgState newPrg)
	{
		elems.add(newPrg);
	}
	
	@Override
	public List<PrgState> getElems() {
        return this.elems;
    }
	
	@Override
    public void setElems(List<PrgState> list) {
        this.elems = list;
    }
	
	@Override
	public void logPrgStateExec(PrgState state) throws FileExceptions, VariableNotDefined {

        try(PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)))){

            iStack<iStatement> stack = state.getexeStack();
            iDictionary<String, Integer> dict = state.getSymtable();
            iList<Integer> out = state.getOutput();
            iHeap<Integer, Integer> garbageCollector = state.getHeap();
            iFileTable<Integer, FileData> fileTable = state.getFileTable();
            iLockTable<Integer, Integer> locktable = state.getLockTable();
            int id = state.getID();
            
            logFile.println(""+ id + "\n");

            logFile.println("ExeStack: \n");
            for(iStatement S: stack.getAll())
                logFile.println(""+ S);


            logFile.println("\nSymTable: \n");
            for(String key: dict.getAllKeys())
                logFile.println(key + "-->" + dict.getValue(key) +'\n');

            logFile.println("\nOutput: \n");
            for(Integer i: out.getAll())
                logFile.println(""+i+"\n" );

            
            logFile.println("\nFile Table: \n");
            for(Integer i: fileTable.getAllKeys())
                logFile.println("" + i + "-->" + fileTable.get(i).getFileName() + "\n");
            
            logFile.println("\nGarbage Collector: \n");
            for(Integer key: garbageCollector.getElems())
                logFile.println(key + "-->" + garbageCollector.getValue(key) +'\n');

            logFile.println("\nLock Table: \n");
            for(Integer key: locktable.getElems())
                logFile.println(key + "-->" + locktable.getValue(key) +'\n');

            logFile.println("\n");

        } catch (FileNotFoundException e) {
            throw new FileExceptions("File not found in PrintWriter");
        } catch (IOException e) {
            throw new FileExceptions("IO exception at PrintWriter");
        }

    }
	
}
