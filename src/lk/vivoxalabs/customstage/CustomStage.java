package lk.vivoxalabs.customstage;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.vivoxalabs.customstage.view.controller.CustomStageController;

import java.net.URL;

/**
 * A fully user customizable JavaFX Stage
 * All of the customizing methods and methods which changing the appearance of the scene executed on
 * FX-threads so user does not have to call these methods inside a,
 * @code Platform.runLater(()->{//code})
 *
 * Created by oshan on 08-Mar-18.
 *
 * @author oshan
 * @version 1.0
 */
public class CustomStage extends Stage {
    private final CustomStageController _STAGE_CONTROLLER_;

    CustomStage(CustomStageController controller){
        _STAGE_CONTROLLER_ = controller;
    }

    /**
     * Changes the current view of the Stage to the given view (pane)
     *
     * @param pane root pane of the loaded fxml view
     */
    public void changeScene(Pane pane){
        _STAGE_CONTROLLER_.changeScene(pane);
    }

    /**
     * Sets the title of the title-bar
     *
     * @param title title for the window
     */
    public void setWindowTitle(String title){
        Platform.runLater(()-> _STAGE_CONTROLLER_.setTitle(title));
    }

    /**
     * Changes the color of the window
     *
     * @param color name/hex/rgb/rgba value of the color
     */
    public void setWindowColor(String color){
        Platform.runLater(()-> _STAGE_CONTROLLER_.setStyle(CustomStageController.StageComponent.WINDOW_COLOR,color));
    }

    /**
     * Changes the color of the color in title-bar
     *
     * @param color name/hex/rgb/rgba value of the color
     */
    public void setTitleColor(String color){
        Platform.runLater(()-> _STAGE_CONTROLLER_.setStyle(CustomStageController.StageComponent.TITLE_TEXT_FILL,color));
    }

    /**
     * Style the CustomStage as to the user given stylesheet
     *
     * @param path URL of the stylesheet
     */
    public void setStyleSheet(URL path) {
        _STAGE_CONTROLLER_.setStyleSheet(path);
    }

    /**
     * Sets a static navigation pane (right side of the window) attaching the pane given
     *
     * @param navigationPane root pane of the navigation (fxml file)
     */
    public void setNavigationPane(Pane navigationPane){
        Platform.runLater(()-> _STAGE_CONTROLLER_.setNavigationPane(navigationPane));
    }

}
