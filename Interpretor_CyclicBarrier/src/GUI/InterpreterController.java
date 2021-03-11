package GUI;

import Controller.Controller;
import Model.ProgramState;
import Model.adt.IBarrierTable;
import Model.adt.IDictionary;
import Model.adt.IHeap;
import Model.statement.IStatement;
import Model.value.IValue;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import Exception.InterpretorException;
public class InterpreterController implements Initializable {

    public InterpreterController() {}

    private Integer selectedProgramID;
    private ProgramState selectedProgram;

    @FXML
    public TableView<Map.Entry<Integer, String>> heapTableView=new TableView<>();

    @FXML
    public TableColumn<Map.Entry<Integer, String>, Integer> heapTableViewC1=new TableColumn<>();

    @FXML
    public TableColumn<Map.Entry<Integer, String>, String> heapTableViewC2=new TableColumn<>();

    @FXML
    public ListView<String> outputListView=new ListView<>();

    @FXML
    public ListView<String> fileTableListView=new ListView<>();

    @FXML
    public ListView<Integer> programStateListView=new ListView<>();

    @FXML
    public TableView<Map.Entry<String, String>> symbolTableView=new TableView<>();

    @FXML
    public TableColumn<Map.Entry<String, String>, String> symbolTableViewC1=new TableColumn<>();

    @FXML
    public TableColumn<Map.Entry<String, String>, String> symbolTableViewC2=new TableColumn<>();

    @FXML
    public TableView<Map.Entry<Integer, Pair<Integer, List<Integer>>>> barrierTableView=new TableView<>();

    @FXML
    public TableColumn<Map.Entry<Integer, Pair<Integer, List<Integer>>>, Integer> barrierTableViewC1=new TableColumn<>();

    @FXML
    public TableColumn<Map.Entry<Integer, Pair<Integer, List<Integer>>>, Integer> barrierTableViewC2=new TableColumn<>();

    @FXML
    public TableColumn<Map.Entry<Integer, Pair<Integer, List<Integer>>>, List<Integer>> barrierTableViewC3=new TableColumn<>();

    @FXML
    public ListView<String> executionStackListView=new ListView<>();

    @FXML
    public TextField numberOfProgramStates=new TextField("");

    @FXML
    public Button runOneStepButton;

    @FXML
    public void setSelectedProgram(){
        if(this.programStateListView.getSelectionModel().getSelectedIndex()>=0 && this.programStateListView.getSelectionModel().getSelectedIndex()<=this.controller.getPrograms().size()){
            this.selectedProgramID=this.programStateListView.getSelectionModel().getSelectedItem();
            this.selectedProgram=this.controller.getPrograms().get(this.programStateListView.getSelectionModel().getSelectedIndex());
            this.numberOfProgramStates.setText(String.valueOf(this.controller.getPrograms().size()));
            this.loadData();
        }
    }

    @FXML
    public void runOneStep(){
        if(this.controller == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "The program was not selected!", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        boolean done=this.selectedProgram.getExecutionStack().isEmpty();
        if(done){
            Alert alert = new Alert(Alert.AlertType.ERROR, "The program is done!", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        try {
            this.controller.executeOneStep();
        }
        catch(InterpretorException e){
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();

            //Stage stage=(Stage) this.runOneStepButton.getScene().getWindow();
            //stage.close();
        }

        this.loadData();
    }

    private Controller controller;

    private void loadData(){
        this.numberOfProgramStates.setText(String.valueOf(this.controller.getPrograms().size()));
        this.programStateListView.getItems().setAll( this.controller.getPrograms().stream().map(ProgramState::getProgramId).collect(Collectors.toList()) );

        if(this.selectedProgram!=null){

            this.outputListView.getItems().setAll( this.selectedProgram.getOutputList().getAll().stream().map(Object::toString).collect(Collectors.toList()));

            this.fileTableListView.getItems().setAll(String.valueOf(this.selectedProgram.getFileTable().getKeySet()));

            List<String> executionStackList=this.selectedProgram.getExecutionStack().getAll().stream().map(IStatement::toString).collect(Collectors.toList());
            Collections.reverse(executionStackList);
            this.executionStackListView.getItems().setAll(executionStackList);

            IHeap<Integer, IValue> heapTable=this.selectedProgram.getMemoryHeap();
            List<Map.Entry<Integer, String>> heapTableList=new ArrayList<>();
            for(Map.Entry<Integer, IValue> element:heapTable.getContent().entrySet()){
                Map.Entry<Integer, String> el=new AbstractMap.SimpleEntry<Integer, String>(element.getKey(),element.getValue().toString());
                heapTableList.add(el);
            }
            this.heapTableView.setItems(FXCollections.observableList(heapTableList));
            this.heapTableView.refresh();

            IDictionary<String, IValue> symbolTable=this.selectedProgram.getSymbolTable();
            List<Map.Entry<String, String>> symbolTableList=new ArrayList<>();
            for(Map.Entry<String, IValue> element:symbolTable.getDictionary().entrySet()){
                Map.Entry<String, String> el=new AbstractMap.SimpleEntry<String, String>(element.getKey(),element.getValue().toString());
                symbolTableList.add(el);
            }
            this.symbolTableView.setItems(FXCollections.observableList(symbolTableList));
            this.symbolTableView.refresh();

            IBarrierTable<Integer, Pair<Integer, List<Integer>>> barrierTable=this.selectedProgram.getBarrierTable();
            List<Map.Entry<Integer, Pair<Integer, List<Integer>>>> barrierTableList=new ArrayList<>();
            for(Map.Entry<Integer, Pair<Integer, List<Integer>>> element:barrierTable.getContent().entrySet()){
                barrierTableList.add(element);
            }
            this.barrierTableView.setItems(FXCollections.observableList(barrierTableList));
            this.barrierTableView.refresh();
        }
    }

    void setController(Controller controller){
        this.controller=controller;

        this.selectedProgram=this.controller.getPrograms().get(0);
        this.selectedProgramID=this.selectedProgram.getProgramId();

        this.loadData();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.heapTableViewC1.setCellValueFactory(p->new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        this.heapTableViewC2.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getValue()+""));

        this.symbolTableViewC1.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getKey()+""));
        this.symbolTableViewC2.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getValue()+""));

        this.barrierTableViewC1.setCellValueFactory(p->new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        this.barrierTableViewC2.setCellValueFactory(p->new SimpleIntegerProperty(p.getValue().getValue().getKey()).asObject());
        this.barrierTableViewC3.setCellValueFactory(p->new SimpleObjectProperty<>(p.getValue().getValue().getValue()));
    }
}
