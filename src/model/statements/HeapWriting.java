package model.statements;

import exceptions.DivisionByZero;
import exceptions.InvalidOperator;
import exceptions.VariableNotDefined;
import model.PrgState;
import model.expression.iExpression;
import model.utils.iDictionary;
import model.utils.iHeap;

public class HeapWriting implements iStatement{
	private String varName;
    private iExpression exp;

    public HeapWriting(String varName, iExpression exp) {
        this.varName = varName;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws VariableNotDefined, DivisionByZero, InvalidOperator {
        iDictionary<String, Integer> dict = state.getSymtable();
        iHeap<Integer, Integer> heap = state.getHeap();

        if(!dict.exists(varName))
            throw new VariableNotDefined("This key is not in our symTable");
        int heapID = dict.getValue(varName);

        if(!heap.exists(heapID))
            throw new VariableNotDefined("This key is not in our heapTable");

        int val = exp.eval(dict, heap);
        heap.update(heapID, val);

        return null;
    }

    @Override
    public String toString(){
        return "heapWriting("+varName+","+exp+")";
    }
}
