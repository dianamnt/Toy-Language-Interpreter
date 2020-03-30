
package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FileData;
import model.PrgState;
import model.iFileTable;
import model.statements.*;
import model.utils.*;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import exceptions.*;
import repository.*;

public class Controller {
    private iRepository repo;
    private ExecutorService executor;

    Controller() {
    }

    public Controller(iRepository repo) {
        this.repo = repo;
        executor = Executors.newFixedThreadPool(2);
    }

    public ObservableList<String> getPrgStatesID() {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (PrgState i : repo.getElems())
            list.add(String.valueOf(i.getID()));

        return list;
    }

    public void addProgram(PrgState newPrg) {
        repo.addPrg(newPrg);
    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream().filter(p -> p.isNotCompleted()).collect(Collectors.toList());
    }

    Map<Integer, Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer, Integer> heap) {
        return heap.entrySet().stream().filter(e -> symTableValues.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void oneStepForAll(List<PrgState> list) throws ControllerException, VariableNotDefined, FileExceptions {


        List<Callable<PrgState>> callList = list.stream()
                .map((PrgState p) -> (Callable<PrgState>) (() -> {
                    return p.oneStep();
                }))
                .collect(Collectors.toList());


        List<PrgState> newList = null;
        try {
            newList = executor.invokeAll(callList)
                    .stream()
                    .map(future ->
                    {
                        try {
                            return future.get();
                        } catch (InterruptedException e) {

                            throw new ControllerException(e.getMessage());


                        } catch (ExecutionException e) {

                            throw new ControllerException(e.getMessage());
                        }


                    })
                    .filter(p -> p != null)
                    .collect(Collectors.toList());
        } catch (InterruptedException e) {
            throw new ControllerException(e.getMessage());
        }


        list.forEach(prg -> repo.logPrgStateExec(prg));
        list.addAll(newList);
        repo.setElems(list);

    }

    public int noPrgStates() {
        return repo.getElems().size();
    }

    public PrgState getPrgStateByIndex(int index) {
        return repo.getElems().get(index);
    }

    public void allStep() throws ControllerException, VariableNotDefined, FileExceptions {
        executor = Executors.newFixedThreadPool(2);

        List<PrgState> prgList = removeCompletedPrg(repo.getElems());

        prgList.forEach(prg -> repo.logPrgStateExec(prg));

        while (prgList.size() > 0) {
            prgList.forEach(p -> {
                p.getHeap().setElems(conservativeGarbageCollector(p.getSymtable().getAllValues(), p.getHeap().getElemss()));
            });
            oneStepForAll(prgList);
            prgList = removeCompletedPrg(repo.getElems());

        }

        executor.shutdownNow();

        prgList.forEach(p -> {
            p.getFileTable().getAllValues().stream().forEach(e -> {
                try {
                    e.getReader().close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        });

        repo.setElems(prgList);
    }

    public boolean oneStepGUI() {

        List<PrgState> prgList = removeCompletedPrg(repo.getElems());

        if (prgList.size() > 0) {
            oneStepForAll(prgList);
            prgList = removeCompletedPrg(repo.getElems());
            return true;
        } else {
            executor.shutdownNow();
            repo.setElems(prgList);
            return false;
        }

    }
}
