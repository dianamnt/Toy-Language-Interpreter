package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.expression.arithmeticExp;
import model.expression.constantExp;
import model.expression.variableExp;
import model.statements.CloseRFile;
import model.statements.OpenRFile;
import model.statements.ReadFile;
import model.statements.HeapAllocation;
import model.expression.HeapReading;
import model.statements.HeapWriting;
import model.statements.*;
import model.expression.booleanExp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SelectProgramController {

    @FXML
    private ListView<String> listView;

    @FXML
    private Button runProgramBTN;

    public static iStatement statement;
    private List<iStatement> StmtList = new ArrayList<iStatement>();


    @FXML
    public void initialize() {

        //hardcodam programe

        iStatement originalProgram1 = new ifStmt(new constantExp(0), new compStmt(new assignStmt("v", new constantExp(5)), new printStmt(new arithmeticExp('/', new variableExp("v"), new constantExp(3)))), new printStmt(new constantExp(2)));

        iStatement originalProgram2  = new compStmt(new assignStmt("v",new constantExp(14)),new printStmt(new variableExp("v")));

        iStatement ifStmnt = new ifStmt(new variableExp("var_c"), new compStmt(new ReadFile(new variableExp("var_f"), "var_c"), new printStmt(new variableExp("var_c"))), new printStmt(new constantExp(0)));
        iStatement opStmnt = new OpenRFile("file3.txt", "var_f");
        iStatement readStmnt = new ReadFile(new variableExp("var_f"), "var_c");
        iStatement comp1 = new compStmt(readStmnt, new printStmt(new variableExp("var_c")));
        iStatement closeFile = new CloseRFile(new variableExp("var_f"));
        iStatement originalProgram3 = new compStmt(opStmnt, new compStmt(readStmnt, new compStmt(new printStmt(new variableExp("var_c")), new compStmt(ifStmnt, closeFile))));

        iStatement stmt1 = new assignStmt("v",new constantExp(10));
        iStatement stmt2 = new HeapAllocation("v", new constantExp(20));
        iStatement stmt3 = new HeapAllocation("a", new constantExp(22));
        iStatement stmt4 = new HeapWriting("a", new constantExp(30));
        iStatement stmt5 = new printStmt(new variableExp("a"));
        iStatement stmt6 = new printStmt(new HeapReading("a"));
        iStatement stmt7 = new assignStmt("a", new constantExp(0));
        iStatement originalProgram4 = new compStmt(stmt1, new compStmt(stmt2, new compStmt(stmt3, new compStmt(stmt4, new compStmt(stmt5, new compStmt(stmt6, stmt7))))));

        iStatement a1 =  new assignStmt("v", new constantExp(6));
        iStatement a2 = new whileStmt(new arithmeticExp('-',new variableExp("v"), new constantExp(4) ),new compStmt(new printStmt(new booleanExp(new variableExp("v"),new constantExp(5),"<=")), new assignStmt("v", new arithmeticExp('-',new variableExp("v"), new constantExp(1)))));
        iStatement a3 = new printStmt(new variableExp("v"));
        iStatement originalProgram5 = new compStmt(new compStmt(a1,a2), a3);

        iStatement originalProgram6 = new compStmt(new assignStmt("v", new constantExp(10)), new compStmt(new HeapAllocation("a",new constantExp(22)), new compStmt(new forkStmt(new compStmt(new HeapWriting("a",new constantExp(30)),new compStmt(new assignStmt("v",new constantExp(32)), new compStmt(new printStmt(new variableExp("v")),new printStmt(new HeapReading("a")))))),new compStmt(new printStmt(new variableExp("v")), new printStmt(new HeapReading("a"))))));


        StmtList.add(originalProgram1);StmtList.add(originalProgram2);StmtList.add(originalProgram3);StmtList.add(originalProgram4);StmtList.add(originalProgram5);StmtList.add(originalProgram6);


        ObservableList<String> list = FXCollections.observableArrayList();
        for(iStatement i : StmtList)
            list.add(""+i);

        listView.setItems(list);

        listView.getSelectionModel().selectIndices(0);
    }

    @FXML
    public void buttonRun() throws IOException {

        statement = StmtList.get(listView.getSelectionModel().getSelectedIndex());

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("runProgram.fxml"));
        stage.setTitle("Running Program");
        stage.setScene(new Scene(root, 800, 600));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }

}