package model.statements;

import exceptions.DivisionByZero;
import exceptions.InvalidOperator;
import exceptions.VariableNotDefined;
import model.PrgState;
import model.utils.iDictionary;
import model.utils.iHeap;
import model.utils.iLockTable;
import model.utils.iStack;

public class unlockStmt implements iStatement {
    private String var;

    public unlockStmt(String var)
    {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws VariableNotDefined, DivisionByZero, InvalidOperator {
        iDictionary<String, Integer> dict = state.getSymtable();
        iHeap<Integer, Integer> heap = state.getHeap();
        iLockTable<Integer,Integer> lockTable = state.getLockTable();

        int identifier = state.getID();

        if(!dict.exists(var))
            throw new VariableNotDefined("This key is not in our symTable");
        int lockID = dict.getValue(var);

        if(!lockTable.exists(lockID))
            ;
        else if(lockTable.getValue(lockID) == identifier)
            lockTable.update(lockID,-1);

        return null;
    }

    @Override
    public String toString(){
        return "unlock("+var+")";
    }
}
