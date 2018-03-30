package lk.vivoxalabs.customstage.tools;

import javafx.application.Platform;
import javafx.stage.Stage;
import lk.vivoxalabs.customstage.CustomStage;
import lk.vivoxalabs.customstage.CustomStageBuilder;

import java.awt.*;

/**
 * Controls all the action events of the window (close,maximize/restore,minimize)
 *
 * @author oshan
 * @version 1.0
 */
public class ActionAdapter {

    private Stage stage;

    public ActionAdapter(Stage stage){
        this.stage=stage;
    }

    /**
     * Close buttons action event
     */
    public void close(){
        Platform.exit();
        System.exit(0);
    }

    /**
     * Minimize buttons action event
     */
    public void minimize(){
        if(stage.isIconified())
            stage.setIconified(false);
        else
            stage.setIconified(true);
    }

    /**
     * Window maximize event
     */
    public void maximize(){
        Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        stage.setMaximized(true);
        stage.setHeight(winSize.height);
        stage.setWidth(winSize.width);
        System.gc();
    }

    /**
     * Restores window
     */
    public void restore(){
        stage.setMaximized(false);
    }
}
