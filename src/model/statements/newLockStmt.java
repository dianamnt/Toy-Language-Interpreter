package model.statements;

import exceptions.DivisionByZero;
import exceptions.InvalidOperator;
import exceptions.VariableNotDefined;
import model.GenHeapID;
import model.PrgState;
import model.expression.iExpression;
import model.utils.iDictionary;
import model.utils.iHeap;
import model.utils.iLockTable;

public class newLockStmt implements iStatement{
    private String var;

    public newLockStmt(String var) {
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZero, InvalidOperator, VariableNotDefined {

        iDictionary<String, Integer> dict = state.getSymtable();
        //iHeap<Integer, Integer> heap = state.getHeap();
        iLockTable<Integer,Integer> lockTable = state.getLockTable();

        int id = GenLockID.getID();

        lockTable.add(id, dict.getValue(var));
        if(dict.exists(var))
            dict.setValue(var, id);
        else
            dict.setValue(var, id);


        return null;
    }

    @Override
    public String toString(){
        return "newLock(" + var + ")";
    }

}
