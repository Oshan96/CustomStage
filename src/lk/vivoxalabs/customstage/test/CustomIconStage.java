package lk.vivoxalabs.customstage.test;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lk.vivoxalabs.customstage.CustomStage;
import lk.vivoxalabs.customstage.CustomStageBuilder;

/**
 * Created by oshan on 12-Mar-18.
 *
 * @author oshan
 */
public class CustomIconStage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        CustomStage stage = new CustomStageBuilder()
                .setIcon("/lk/vivoxalabs/customstage/test/logo.png")
                //Icons for close,minimize,maximize/restore buttons
                //Icon size should be equal or lower than 30px
                .setActionIcons(new Image("/lk/vivoxalabs/customstage/test/btnClose.png"),
                        new Image("/lk/vivoxalabs/customstage/test/btnMinimize.png"),
                        new Image("/lk/vivoxalabs/customstage/test/btnMaximize.png"),
                        new Image("/lk/vivoxalabs/customstage/test/btnRestore.png")) //change default icons for action buttons
                .setWindowTitle("Custom Icons")
                //Title color
                .setTitleColor("white")
                //Window color (Transparent)
                .setWindowColor("gray") //With transparency (hex value [with alpha] can also be used)
                .setButtonHoverColor("lightgray")
                //Builds the CustomStage
                .build();

        stage.show();
    }
}
