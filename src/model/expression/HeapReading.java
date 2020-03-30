package model.expression;

import exceptions.VariableNotDefined;
import model.utils.iDictionary;
import model.utils.iHeap;

public class HeapReading implements iExpression{
	private String varName;

    public HeapReading(String varName) {
        this.varName = varName;
    }


    @Override
    public int eval(iDictionary<String, Integer> dict, iHeap<Integer, Integer> heap) throws VariableNotDefined {

        if(!dict.exists(varName)) {

            throw new VariableNotDefined("This key is not in our symTable");
        }
        int heapID = dict.getValue(varName);

        if(!heap.exists(heapID)) {

            throw new VariableNotDefined("This key is not in our heapTable");
        }

        return  heap.getValue(heapID);
    }

    @Override
    public String toString(){
        return "heapReading(" + varName +")";
    }
}
