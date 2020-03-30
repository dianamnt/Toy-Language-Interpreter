package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.PrgState;
import model.*;
import model.statements.iStatement;
import model.statements.GenIDFork;
import repository.Repository;
import model.utils.*;

import utilsTables.*;

import static java.lang.System.exit;

public class RunProgramController {
    //HEAP TABLE
    @FXML private TableView<HeapTableView> heapTable;
    @FXML private TableColumn<HeapTableView, Integer> heapAddressColumn;
    @FXML private TableColumn<HeapTableView, Integer> heapValueColumn;

    //LOCK TABLE
    @FXML private TableView<LockTableView> lockTable;
    @FXML private TableColumn<LockTableView, Integer> lockLocationColumn;
    @FXML private TableColumn<LockTableView, Integer> lockValueColumn;

    //FILE TABLE
    @FXML private TableView<FileTableView> fileTable;
    @FXML private TableColumn<FileTableView, Integer> fileTableIdentifier;
    @FXML private TableColumn<FileTableView, String> fileTableName;

    //SYM TABLE
    @FXML private TableView<SymTableView> symTable;
    @FXML private TableColumn<SymTableView, String> symTableVarName;
    @FXML private TableColumn<SymTableView, Integer> symTableValue;

    @FXML private ListView<String> outList;
    @FXML private ListView<String> exeStack;
    @FXML private ListView<String> prgStateIdentifiers;
    @FXML private Button oneStepBTN;
    @FXML private TextField noPrgStateTextField;

    private Controller ctrl;




    @FXML
    public void initialize(){

        iStack<iStatement> exeStack = new MyStack<>();
        iDictionary<String, Integer> dict = new MyDictionary<>();
        iList<Integer> list = new MyList<>();
        iFileTable<Integer, FileData> fileTable = new FileTable<>();
        iHeap<Integer, Integer> heap = new MyHeap<>();
        iLockTable<Integer,Integer> lockTable = new MyLockTable<>();
        exeStack.push(SelectProgramController.statement);
        PrgState programState = new PrgState(exeStack, dict, list, null, fileTable, heap, GenIDFork.getID(),lockTable);

        Repository repo = new Repository("wefwfwf.txt");
        repo.addPrg(programState);
        ctrl = new Controller(repo);


        this.heapAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        this.heapValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        this.fileTableIdentifier.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.fileTableName.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        this.symTableVarName.setCellValueFactory(new PropertyValueFactory<>("varName"));
        this.symTableValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        this.lockLocationColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        this.lockValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        setNoPrgState();
        setPrgStateIdentifiers();
        prgStateIdentifiers.getSelectionModel().select(0);
        setExeStack();
    }



    private void setNoPrgState(){
        Integer nr = ctrl.noPrgStates();
        noPrgStateTextField.setText(String.valueOf(nr));
    }

    private void setPrgStateIdentifiers(){
        prgStateIdentifiers.setItems(ctrl.getPrgStatesID());
    }

    PrgState getCurrentProgramState(){
        int index = prgStateIdentifiers.getSelectionModel().getSelectedIndex();
        if(index == -1)
            index = 0;
        return ctrl.getPrgStateByIndex(index);
    }

    private void setExeStack(){

        ObservableList<String> list = FXCollections.observableArrayList();
        PrgState programState = getCurrentProgramState();


        for(iStatement i : programState.getexeStack().getAll())
            list.add(""+i);

        exeStack.setItems(list);
    }

    private void setHeapTable(){

        ObservableList<HeapTableView> list = FXCollections.observableArrayList();
        PrgState programState = getCurrentProgramState(); // here we don't need to get current because heap is shared by all
        // but i used to don't make another function to get one programState

        for(Integer key: programState.getHeap().getElems())
            list.add(new HeapTableView(key, programState.getHeap().getValue(key)));

        heapTable.setItems(list);
    }

    private void setLockTable(){

        ObservableList<LockTableView> list = FXCollections.observableArrayList();
        PrgState programState = getCurrentProgramState(); // here we don't need to get current because heap is shared by all
        // but i used to don't make another function to get one programState

        for(Integer key: programState.getLockTable().getElems())
            list.add(new LockTableView(key, programState.getLockTable().getValue(key)));

        lockTable.setItems(list);
    }

    private void setFileTable(){

        ObservableList<FileTableView> list = FXCollections.observableArrayList();
        PrgState programState = getCurrentProgramState(); // here we don't need to get current because fileTable is shared by all
        // but i used to don't make another function to get one programState

        for(Integer key: programState.getFileTable().getAllKeys())
            list.add(new FileTableView(key, programState.getFileTable().get(key).getFileName()));

        fileTable.setItems(list);
    }

    private void setSymTable(){

        ObservableList<SymTableView> list = FXCollections.observableArrayList();
        PrgState programState = getCurrentProgramState();

        for(String key: programState.getSymtable().getAllKeys())
            list.add(new SymTableView(key, programState.getSymtable().getValue(key)));

        symTable.setItems(list);
    }

    private void setOutList(){
        ObservableList<String> list = FXCollections.observableArrayList();
        PrgState programState = getCurrentProgramState();   // they all share the same outList

        for(Integer i: programState.getOutput().getAll())
            list.add(""+i);

        outList.setItems(list);
    }


    private void setAll(){
        setNoPrgState();
        setPrgStateIdentifiers();
        setExeStack();
        setHeapTable();
        setLockTable();
        setFileTable();
        setSymTable();
        setOutList();
    }



    public void executeOneStep(ActionEvent ae){

        try {
            if (ctrl.oneStepGUI()) {
                setAll();
            } else {
                setNoPrgState();
                setPrgStateIdentifiers();
            }
        }
        catch (RuntimeException e){
            Node source = (Node) ae.getSource();
            Stage theStage = (Stage) source.getScene().getWindow();
            Alert a = new Alert(Alert.AlertType.ERROR, e.getMessage());
            a.show();
            theStage.close();
        }
    }

    public void mouseClickPrgStateIdentifiers(){

        if(ctrl.noPrgStates() > 0)
            setAll();
    }
}
