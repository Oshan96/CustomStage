package lk.vivoxalabs.customstage.test;

import javafx.application.Application;
import javafx.stage.Stage;
import lk.vivoxalabs.customstage.CustomStage;
import lk.vivoxalabs.customstage.CustomStageBuilder;

/**
 * Created by oshan on 12-Mar-18.
 *
 * @author oshan
 */
public class TransparentStage extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        CustomStage transparentStage = new CustomStageBuilder()
                .setIcon("logo.png")
                .setWindowTitle("Custom Stage Transparent")
                //Title color
                .setTitleColor("white")
                //Window color (Transparent)
                .setWindowColor("rgba(34,54,122,0.6)") //With transparency (hex value [with alpha] can also be used)
                .setButtonHoverColor("lightgray")
                //Builds the CustomStage
                .build();

        transparentStage.show();
    }
}
