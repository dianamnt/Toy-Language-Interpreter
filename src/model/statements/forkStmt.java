package model.statements;

import model.FileData;
import model.PrgState;
import model.iFileTable;
import model.utils.MyStack;
import model.utils.iDictionary;
import model.utils.iHeap;
import model.utils.iList;
import model.utils.iStack;
import model.utils.iLockTable;


public class forkStmt implements iStatement{
	private iStatement statement;

    public forkStmt(iStatement statement) {
        this.statement = statement;
    }


    @Override
    public PrgState execute(PrgState state) {

        iStack<iStatement> exestack = new MyStack<iStatement>();
        iDictionary<String,Integer> symtable = state.getSymtable().copy();
        iList<Integer> output = state.getOutput();
        iHeap<Integer,Integer> garbageColector = state.getHeap();
        iFileTable<Integer,FileData> fileTable = state.getFileTable();
        iLockTable<Integer,Integer> lockTable = state.getLockTable();

        exestack.push(statement);
        int ID = GenIDFork.getID();
        return new PrgState(exestack,symtable,output,null,fileTable,garbageColector,ID,lockTable);

    }

    @Override
    public String toString(){
        return "fork("+statement+")";
    }
}
