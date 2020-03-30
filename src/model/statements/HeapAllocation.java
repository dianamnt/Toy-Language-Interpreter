package model.statements;

import exceptions.DivisionByZero;
import exceptions.InvalidOperator;
import exceptions.VariableNotDefined;
import model.GenHeapID;
import model.PrgState;
import model.expression.iExpression;
import model.utils.iDictionary;
import model.utils.iHeap;

public class HeapAllocation implements iStatement {
	private String varName;
    private iExpression exp;

   public HeapAllocation(String varName, iExpression exp) {
       this.varName = varName;
       this.exp = exp;
   }

   @Override
   public PrgState execute(PrgState state) throws DivisionByZero, InvalidOperator, VariableNotDefined {

       iDictionary<String, Integer> dict = state.getSymtable();
       iHeap<Integer, Integer> heap = state.getHeap();

       int id = GenHeapID.getID();
       int val = exp.eval(dict, heap);

       heap.add(id, val);
       if(dict.exists(varName))
               dict.setValue(varName, id);
       else
                dict.setValue(varName, id);


       return null;
   }

   @Override
    public String toString(){
       return "NewH(" + varName + ", " + exp + ")";
    }
}
