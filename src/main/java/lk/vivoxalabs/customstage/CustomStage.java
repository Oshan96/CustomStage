package lk.vivoxalabs.customstage;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.vivoxalabs.customstage.tools.NavigationType;
import lk.vivoxalabs.customstage.view.controller.CustomStageController;
import lk.vivoxalabs.scenemanager.SceneManager;

import java.awt.*;
import java.net.URL;

/**
 * A fully user customizable JavaFX Stage
 * All of the customizing methods and methods which changing the appearance of the scene executed on
 * FX-threads so user does not have to call these methods inside a Platform.runlater
 *
 * @author oshan
 * @version 1.1.1
 */
public class CustomStage extends Stage {
    private final CustomStageController _STAGE_CONTROLLER_;
    private static final SceneManager DEFAULT_SCENE_MANAGER;

    static{
        DEFAULT_SCENE_MANAGER = new SceneManager();
    }

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
     * @param type where the navigationPane should be placed on the window (LEFT/RIGHT/TOP/BOTTOM)
     * @param navigationPane root pane of the navigation (fxml file)
     */
    public void setNavigationPane(NavigationType type, Pane navigationPane){
        Platform.runLater(()-> _STAGE_CONTROLLER_.setNavigationPane(type,navigationPane));
    }

    /**
     * Removes the pointed navigationPane from the window
     *
     * @param type which navigationPane should be removed from the window (LEFT/RIGHT/TOP/BOTTOM)
     */
    public void removeNavigationPane(NavigationType type){
        Platform.runLater(()->_STAGE_CONTROLLER_.removeNavigationPane(type));
    }

    /**
     * This method shall be called if a dynamic navigationPane is in use.
     * Will call either drawer.open() or drawer.hide() method depending on the drawer's current showing state.
     *
     * <p>
     *     <b>
     *           It is recommended to use this event especially if you're using NavigationType.TOP navigation, inorder to avoid
     *           the navigationPane from overlapping the title-bar on its drawer.hider() call.
     *      </b>
     * </p>
     *
     *
     * @param type the navigationPane which the event should be triggered for
     */
    public void dynamicDrawerEvent(NavigationType type){
        _STAGE_CONTROLLER_.dynamicDrawerEvent(type);
    }

    /**
     * @return the static SceneManager object in CustomStage
     */
    public static SceneManager getDefaultSceneManager(){
        return DEFAULT_SCENE_MANAGER;
    }

}
