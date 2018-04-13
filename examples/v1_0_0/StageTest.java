package v1_0_0;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import lk.vivoxalabs.customstage.CustomStage;
import lk.vivoxalabs.customstage.CustomStageBuilder;

/**
 * Created by oshan on 08-Mar-18.
 *
 * @author oshan
 */
public class StageTest extends Application{

    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Whole usage of CustomStage

        CustomStage stage = new CustomStageBuilder()
                .setWindowTitle("CustomStage example")
                .setWindowColor("rgb(34,54,122)") 
                .build();

        stage.show();

        //Change the scene of the window
        //stage.changeScene(FXMLLoader.load(getClass().getResource("Dashboard.fxml")));
    }
}
