package lk.vivoxalabs.customstage;

import com.sun.istack.internal.Nullable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import lk.vivoxalabs.customstage.tools.ActionAdapter;
import lk.vivoxalabs.customstage.tools.ResizeHelper;
import lk.vivoxalabs.customstage.view.controller.CustomStageController;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * This is used to create a CustomStage object as per user given definitions (using the methods of this class)
 *
 * Created by oshan on 08-Mar-18.
 *
 * @author oshan
 * @version 1.0
 */
public class CustomStageBuilder {

    public static final Dimension DIMENSION;

    private final CustomStage _STAGE_;
    private final ActionAdapter _ACTION_ADAPTER_;
    private final CustomStageController _STAGE_CONTROLLER_;

    static {
        DIMENSION=Toolkit.getDefaultToolkit().getScreenSize();
    }

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

    /**
     * Sets the title of the title-bar
     *
     * @param title title for the window
     * @return the current CustomStageBuilder object
     */
    public CustomStageBuilder setWindowTitle(String title){
        _STAGE_CONTROLLER_.setTitle(title);
        return this;
    }

    /**
     * The icon for the window to be showed on taskbar
     *
     * @param path path of the image
     * @return the current CustomStageBuilder object
     */
    public CustomStageBuilder setIcon(String path){
        _STAGE_.getIcons().add(new Image(path));
        return this;
    }

    /**
     * Changes the color of the window
     *
     * @param color name/hex/rgb/rgba value of the color
     * @return the current CustomStageBuilder object
     */
    public CustomStageBuilder setWindowColor(String color){
        _STAGE_CONTROLLER_.setStyle(CustomStageController.StageComponent.WINDOW_COLOR,color);
        return this;
    }

    /**
     * Changes the color of the color in title-bar
     *
     * @param color name/hex/rgb/rgba value of the color
     * @return the current CustomStageBuilder object
     */
    public CustomStageBuilder setTitleColor(String color){
        _STAGE_CONTROLLER_.setStyle(CustomStageController.StageComponent.TITLE_TEXT_FILL,color);
        return this;
    }

    /**
     * Changes the color of the close, minimize and maximize/restore buttons
     *
     * @param color name/hex/rgb/rgba value of the color
     * @return the current CustomStageBuilder object
     */
    public CustomStageBuilder setButtonColor(String color){
        _STAGE_CONTROLLER_.setStyle(CustomStageController.StageComponent.BUTTON_COLOR,color);
        return this;
    }

    /**
     * Changes the color of the minimize and maximize/restore buttons on hover (close button's color won't change)
     *
     * @param color name/hex/rgb/rgba value of the color
     * @return the current CustomStageBuilder object
     */
    public CustomStageBuilder setButtonHoverColor(String color){
        _STAGE_CONTROLLER_.setStyle(CustomStageController.StageComponent.BUTTON_HOVER_COLOR,color);
        return this;
    }

    /**
     * Changes the default icons for the action buttons on Title-bar
     *
     * @param close Icon for close button
     * @param minimize Icon for minimize button
     * @param maximize Window maximize (maximize button) icon
     * @param restore Window restore (maximize button) icon
     * @return the current CustomStageBuilder object
     */
    public CustomStageBuilder setActionIcons(@Nullable Image close, @Nullable Image minimize, @Nullable Image maximize, @Nullable Image restore){
        _STAGE_CONTROLLER_.setActionIcons(close,minimize,maximize,restore);
        return this;
    }

    /**
     * Sets the maximum and minimum resizing values for the window.
     * However, these values does not affect maximize buttons behavior.
     *
     * @param minWidth Minimum width of window
     * @param minHeight Minimum height of window
     * @param maxWidth Maximum width of window
     * @param maxHeight Maximum height of window
     * @return the current CustomStageBuilder object
     */
    public CustomStageBuilder setDimensions(double minWidth,double minHeight,double maxWidth,double maxHeight){
        ResizeHelper.addResizeListener(_STAGE_,minWidth,minHeight,maxWidth,maxHeight);
        return this;
    }

    /**
     * Style the CustomStage as to the user given stylesheet
     *
     * @param path URL of the stylesheet
     * @return the current CustomStageBuilder object
     */
    public CustomStageBuilder setStyleSheet(URL path) {
        _STAGE_CONTROLLER_.setStyleSheet(path);
        return this;
    }

    /**
     * Removes the navigation pane of the window
     *
     * @return the current CustomStageBuilder object
     */
    public CustomStageBuilder removeNavigationPane(){
        _STAGE_CONTROLLER_.removeNavigationPane();
        return this;
    }

    /**
     * Sets a static navigation pane (right side of the window) attaching the pane given
     *
     * @param navigationPane root pane of the navigation (fxml file)
     * @return the current CustomStageBuilder object
     */
    public CustomStageBuilder setNavigationPane(Pane navigationPane){
        _STAGE_CONTROLLER_.setNavigationPane(navigationPane);
        return this;
    }

    /**
     * Produces the CustomStage object as for the definitions given by the user
     *
     * @return the CustomStage
     */
    public CustomStage build(){
        _STAGE_CONTROLLER_.setActionAdapter(_ACTION_ADAPTER_);

        _STAGE_.setWidth(DIMENSION.getWidth()/1.2);
        _STAGE_.setHeight(DIMENSION.getHeight()/1.2);

        System.gc();
        return _STAGE_;
    }


}
