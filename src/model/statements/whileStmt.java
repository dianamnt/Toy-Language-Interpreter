package model.statements;

import exceptions.DivisionByZero;
import exceptions.InvalidOperator;
import exceptions.VariableNotDefined;
import model.PrgState;
import model.expression.iExpression;
import model.utils.iDictionary;
import model.utils.iHeap;
import model.utils.iStack;

public class whileStmt implements iStatement{
	iExpression exp;
    iStatement statement;

    public whileStmt(iExpression exp, iStatement statement) {
        this.exp = exp;
        this.statement = statement;
    }


    @Override
    public PrgState execute(PrgState state) throws DivisionByZero, InvalidOperator, VariableNotDefined {

        iStack<iStatement> stack = state.getexeStack();
        iHeap<Integer, Integer> heap = state.getHeap();
        iDictionary<String, Integer> dict = state.getSymtable();

        int val = exp.eval(dict, heap);

        if(val != 0) {
            stack.push(new whileStmt(exp, statement));
            stack.push(statement);
        }

        return null;
    }

    @Override
    public String toString(){
        return "While("+exp+"){"+statement+"}";
    }
}
