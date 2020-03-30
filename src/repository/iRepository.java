package repository;

import java.util.List;

import exceptions.FileExceptions;
import exceptions.VariableNotDefined;
import model.PrgState;
import model.utils.MyList;

public interface iRepository {
	void addPrg(PrgState newPrg);
	//PrgState getCrtPrg();
	List<PrgState> getElems();
	void setElems(List<PrgState> list);
	void logPrgStateExec(PrgState state) throws FileExceptions, VariableNotDefined;
}
