package lk.vivoxalabs.customstage;

import com.sun.istack.internal.Nullable;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import lk.vivoxalabs.customstage.tools.ActionAdapter;
import lk.vivoxalabs.customstage.tools.ResizeHelper;
import lk.vivoxalabs.customstage.view.controller.CustomStageController;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by oshan on 08-Mar-18.
 *
 * @author oshan
 */
public class CustomStageBuilder {

    private final CustomStage _STAGE_;
    private final ActionAdapter _ACTION_ADAPTER_;
    private final CustomStageController _STAGE_CONTROLLER_;

    public CustomStageBuilder() throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/vivoxalabs/customstage/view/fxml/CustomStage.fxml"));
        Scene scene = new Scene(loader.load());
        scene.setFill(Color.TRANSPARENT);

        _STAGE_CONTROLLER_ = loader.getController();
        _STAGE_ = new CustomStage(_STAGE_CONTROLLER_);
        _ACTION_ADAPTER_ = new ActionAdapter(_STAGE_);

        _STAGE_.setScene(scene);

        ResizeHelper.addResizeListener(_STAGE_);

        _STAGE_.initStyle(StageStyle.TRANSPARENT);
        _STAGE_.setAlwaysOnTop(true);
    }

    public CustomStageBuilder setWindowTitle(String title){
        _STAGE_CONTROLLER_.setTitle(title);
        return this;
    }

    public CustomStageBuilder setIcon(String path){
        _STAGE_.getIcons().add(new Image(path));
        return this;
    }

    public CustomStageBuilder setWindowColor(String color){
        _STAGE_CONTROLLER_.setStyle(CustomStageController.StageComponent.WINDOW_COLOR,color);
        return this;
    }

    public CustomStageBuilder setTitleColor(String color){
        _STAGE_CONTROLLER_.setStyle(CustomStageController.StageComponent.TITLE_TEXT_FILL,color);
        return this;
    }

    public CustomStageBuilder setButtonColor(String color){
        _STAGE_CONTROLLER_.setStyle(CustomStageController.StageComponent.BUTTON_COLOR,color);
        return this;
    }

    public CustomStageBuilder setButtonHoverColor(String color){
        _STAGE_CONTROLLER_.setStyle(CustomStageController.StageComponent.BUTTON_HOVER_COLOR,color);
        return this;
    }

    /**
     * Changes the default icons for the action buttons on Title-bar
     * @param close Icon for close button
     * @param minimize Icon for minimize button
     * @param maximize Window maximize (maximize button) icon
     * @param restore Window restore (maximize button) icon
     * @return Current builder
     */
    public CustomStageBuilder setActionIcons(@Nullable Image close, @Nullable Image minimize, @Nullable Image maximize, @Nullable Image restore){
        _STAGE_CONTROLLER_.setActionIcons(close,minimize,maximize,restore);
        return this;
    }

    public CustomStageBuilder setDimensions(double minWidth,double minHeight,double maxWidth,double maxHeight){
        ResizeHelper.addResizeListener(_STAGE_,minWidth,minHeight,maxWidth,maxHeight);
        return this;
    }

    public CustomStageBuilder setStyleSheet(URL path) throws IOException {
        _STAGE_CONTROLLER_.setStyleSheet(path);
        return this;
    }

    public CustomStage build(){
        _STAGE_CONTROLLER_.setActionAdapter(_ACTION_ADAPTER_);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        _STAGE_.setWidth(d.getWidth()/1.2);
        _STAGE_.setHeight(d.getHeight()/1.2);
        d=null;
        System.gc();
        return _STAGE_;
    }


}
