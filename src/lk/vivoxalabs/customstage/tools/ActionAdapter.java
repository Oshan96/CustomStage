package lk.vivoxalabs.customstage.tools;

import javafx.application.Platform;
import javafx.stage.Stage;
import lk.vivoxalabs.customstage.CustomStage;
import lk.vivoxalabs.customstage.CustomStageBuilder;

import java.awt.*;

/**
 * Created by oshan on 08-Mar-18.
 *
 * @author oshan
 */
public class ActionAdapter {

    private Stage stage;

    private Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

    public ActionAdapter(Stage stage){
        this.stage=stage;
    }

    public void close(){
        Platform.exit();
        System.exit(0);
    }

    public void minimize(){
        if(stage.isIconified())
            stage.setIconified(false);
        else
            stage.setIconified(true);
    }

    public void maximize(){
        stage.setMaximized(true);
        stage.setHeight(winSize.height);

    }

    public void restore(){
        stage.setMaximized(false);
    }
}
