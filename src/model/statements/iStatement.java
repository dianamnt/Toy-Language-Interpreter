package model.statements;

import model.PrgState;

import exceptions.*;

public interface iStatement {
	PrgState execute(PrgState state) throws DivisionByZero, InvalidOperator, VariableNotDefined, FileExceptions ;
}
