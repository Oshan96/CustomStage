package examples;

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
                //Minimum, Maximum values of the window size
//                .setDimensions(0,0,1920,1080) // change values the minW,minH,maxW,maxH of the window
                //Icons for close,minimize,maximize/restore buttons
//                .setActionIcons(null,null,null,null) //change default icons for action buttons
                //Application Icon
                .setIcon("logo.png")
                .setStyleSheet(StageTest.class.getResource("testCss.css"))
                //Title of the window
                .setWindowTitle("Custom Stage")
                //Title color
                .setTitleColor("yellow")
                //Window color
                .setWindowColor("rgb(34,54,122)")
                //Window color (Transparent)
//                .setWindowColor("rgba(34,54,122,0.6)") //With transparency (hex value [with alpha] can also be used)
                //Action button colors (close/minimize/maximize)
//                .setButtonColor("#FF56AA") // takes the window's color by default
                //Action button hover colors (close/minimize/maximize)
                .setButtonHoverColor("yellow")
                //Set the navigation pane
//                .setLeft_navigationPane(new AnchorPane())
                //CustomStage without a navigation panel
//                  .removeNavigationPane()
                //Builds the CustomStage
                .build();

        stage.show();

        //Change the scene of the window
        stage.changeScene(FXMLLoader.load(getClass().getResource("Dashboard.fxml")));
    }
}
