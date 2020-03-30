package model.expression;

import exceptions.DivisionByZero;
import exceptions.InvalidOperator;
import exceptions.VariableNotDefined;
import model.utils.iDictionary;
import model.utils.iHeap;

public class equalExp implements iExpression{
	private iExpression st;
	private iExpression dr;
	
    public equalExp(iExpression st, iExpression dr) {
        this.st = st;
        this.dr = dr;
    }


    @Override
    public int eval(iDictionary<String, Integer> d, iHeap<Integer, Integer> heap) throws DivisionByZero, InvalidOperator, VariableNotDefined{
        if(st.eval(d,heap) == dr.eval(d,heap))
            return 1;
        return 0;
    }

    @Override
    public String toString(){
        return "Equal("+st+"=="+dr+") ";
    }
}
