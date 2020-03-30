package model.statements;

import exceptions.DivisionByZero;
import exceptions.InvalidOperator;
import exceptions.VariableNotDefined;
import model.PrgState;
import model.utils.iDictionary;
import model.utils.iHeap;
import model.utils.iLockTable;
import model.utils.iStack;

public class lockStmt implements iStatement{
    private String var;

    public lockStmt(String var)
    {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws VariableNotDefined, DivisionByZero, InvalidOperator {
        iDictionary<String, Integer> dict = state.getSymtable();
        iHeap<Integer, Integer> heap = state.getHeap();
        iLockTable<Integer,Integer> lockTable = state.getLockTable();
        iStack<iStatement> exestack = state.getexeStack();

        int identifier = state.getID();

        if(!dict.exists(var))
            throw new VariableNotDefined("This key is not in our symTable");
        int lockID = dict.getValue(var);

        if(!lockTable.exists(lockID))
            throw new VariableNotDefined("This key is not in our heapTable");
        else if(lockTable.getValue(lockID) == -1)
            lockTable.update(lockID,identifier);
        else
            exestack.push(this);

        return null;
    }

    @Override
    public String toString(){
        return "lock("+var+")";
    }
}
