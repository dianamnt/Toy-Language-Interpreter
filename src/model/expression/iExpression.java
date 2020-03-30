
package model.expression;

import exceptions.DivisionByZero;
import exceptions.InvalidOperator;
import model.utils.iDictionary;
import model.utils.iHeap;
import exceptions.VariableNotDefined;

public interface iExpression {
	public int eval(iDictionary<String, Integer> d, iHeap<Integer, Integer> heap) throws DivisionByZero, InvalidOperator, VariableNotDefined;
}
