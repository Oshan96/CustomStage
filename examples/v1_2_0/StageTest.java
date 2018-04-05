package v1_2_0;

import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.vivoxalabs.customstage.CustomStage;
import lk.vivoxalabs.customstage.CustomStageBuilder;
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
        nav.setStyle("-fx-background-color: gold;");
        CustomStage stage = new CustomStageBuilder()
//                .setDimensions(0,0,1920,1280) // change values the minW,minH,maxW,maxH of the window
//                .setActionIcons(null,null,null,null) //change default icons for action buttons
//                .setIcon("/test/logo.png")
//                .setStyleSheet(new File("test/testCss.css").getAbsolutePath())
//                .setStyleSheet(StageTest.class.getResource("testCss.css"))
                .setWindowTitle("Custom Stage")
                .setTitleColor("yellow")
                .setWindowColor("rgb(34,54,122)")
                //Dynamic navigation pane on left-side of the window with 50px space left from top of the window
                .setNavigationPane(Style.DYNAMIC,NavigationType.LEFT,nav,50,0,false)
//                .setWindowColor("rgba(34,54,122,0.6)") //With transparency (hex value [with alpha] can also be used)
//                .setButtonColor("#FF56AA") // takes the window's color by default
                .setButtonHoverColor("yellow")
                .build();

        stage.show();

        stage.getScene().getRoot().setOnMouseClicked(e->stage.dynamicDrawerEvent(NavigationType.LEFT));

//        stage.changeScene(FXMLLoader.load(getClass().getResource("/test/Dashboard.fxml")));

    }
}
