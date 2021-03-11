package GUI;

import Controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InterpreterWindow {

    public void show(Controller controller) throws Exception{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Interpreter.fxml"));
        Parent interpreterView=loader.load();

        InterpreterController interpreterController=loader.getController();
        interpreterController.setController(controller);

        Stage stage=new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Interpreter - Run Program");
        stage.setScene(new Scene(interpreterView));
        stage.showAndWait();
    }
}
