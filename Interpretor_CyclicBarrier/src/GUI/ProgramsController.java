package GUI;

import Controller.Controller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ProgramsController implements Initializable {

    public ProgramsController() {}

    @FXML
    private ListView<String> programsList=new ListView<>();

    @FXML
    private Button runButton;

    @FXML
    private Button exitButton;

    @FXML
    private void runButtonPressed(){
        if(programsList.getSelectionModel().getSelectedItems()!=null){
            InterpreterWindow interpreterWindow=new InterpreterWindow();
            try{
                Controller controller=this.controllerList.get(this.programsList.getSelectionModel().getSelectedIndex());

                if(!controller.getPassedTypeCheck()){
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setContentText("Program doesn't pass the TypeChecker!");
                    errorAlert.showAndWait();
                }
                else
                    interpreterWindow.show(controller);
            }
            catch (Exception error) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setContentText("No example selected!");
                errorAlert.showAndWait();
            }
        }
        else{
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setContentText("No example selected!");
            errorAlert.showAndWait();
        }
    }

    @FXML
    private void exitButtonPressed(){
        Stage stage=(Stage) this.exitButton.getScene().getWindow();
        stage.close();
    }

    private List<Controller> controllerList;

    public void setProgramsList(List<Controller> programs){
        this.controllerList=programs;
        this.programsList.setItems(FXCollections.observableArrayList(this.controllerList.stream().map(Controller::getDescription).collect(Collectors.toList())));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}
}
