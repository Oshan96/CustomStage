package v1_2_2;

import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.vivoxalabs.customstage.CustomStage;
import lk.vivoxalabs.customstage.CustomStageBuilder;
import lk.vivoxalabs.customstage.tools.HorizontalPos;
import lk.vivoxalabs.customstage.tools.NavigationType;
import lk.vivoxalabs.customstage.tools.Style;

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
        AnchorPane nav = new AnchorPane();
        nav.setStyle("-fx-background-color: lightblue;");
        CustomStage stage = new CustomStageBuilder()
//                .setDimensions(0,0,1920,1280) // change values the minW,minH,maxW,maxH of the window
//                .setActionIcons(null,null,null,null) //change default icons for action buttons

                .setIcon("/v1_2_2/Logo.png")
                .setStyleSheet(StageTest.class.getResource("testCss.css"))
//                .setWindowTitle("Normal Window Title")

                //Give positioning to title and buttons
                .setWindowTitle("Custom Stage Positions",HorizontalPos.LEFT,HorizontalPos.CENTER)

                .setTitleColor("yellow")
                .setWindowColor("rgb(34,54,122)")
                //Dynamic navigation pane on left-side of the window with 50px space left from top of the window
                .setNavigationPane(Style.DYNAMIC,NavigationType.LEFT,nav,50,0,false)
//                .setWindowColor("rgba(34,54,122,0.6)") //With transparency (hex value [with alpha] can also be used)
//                .setButtonColor("#FF56AA") // takes the window's color by default

                //Giver different colors for close,maximize,minimize buttons on hover state
                .setButtonHoverColor("gold","green","red")

                .build();

        stage.show();

        stage.getScene().getRoot().setOnMouseClicked(e->stage.dynamicDrawerEvent(NavigationType.LEFT));

//        stage.changeScene(FXMLLoader.load(getClass().getResource("/test/Dashboard.fxml")));

    }
}
