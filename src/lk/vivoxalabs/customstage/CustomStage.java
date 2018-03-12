package lk.vivoxalabs.customstage;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.vivoxalabs.customstage.view.controller.CustomStageController;

import java.io.IOException;
import java.net.URL;

/**
 * Created by oshan on 08-Mar-18.
 *
 * @author oshan
 */
public class CustomStage extends Stage {
    private final CustomStageController _STAGE_CONTROLLER_;

    public CustomStage(CustomStageController controller){
        _STAGE_CONTROLLER_ = controller;
    }

    public void changeScene(Pane pane){
        _STAGE_CONTROLLER_.changeScene(pane);
    }

    public void setWindowTitle(String title){
        Platform.runLater(()-> _STAGE_CONTROLLER_.setTitle(title));
    }

    public void setWindowColor(String color){
        Platform.runLater(()-> _STAGE_CONTROLLER_.setStyle(CustomStageController.StageComponent.WINDOW_COLOR,color));
    }

    public void setTitleColor(String color){
        Platform.runLater(()-> _STAGE_CONTROLLER_.setStyle(CustomStageController.StageComponent.TITLE_TEXT_FILL,color));
    }

    public void setStyleSheet(URL path) {
        _STAGE_CONTROLLER_.setStyleSheet(path);
    }

    public void setNavigationPane(Pane navigationPane){
        Platform.runLater(()-> _STAGE_CONTROLLER_.setNavigationPane(navigationPane));
    }

}
