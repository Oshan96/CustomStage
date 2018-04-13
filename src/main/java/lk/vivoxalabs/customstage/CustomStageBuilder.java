package lk.vivoxalabs.customstage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import lk.vivoxalabs.customstage.tools.*;
import lk.vivoxalabs.customstage.view.controller.CustomStageController;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * This is used to create a CustomStage object as per user given definitions (using the methods of this class)
 *
 * @author oshan
 * @version 1.1.0
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CustomStage.fxml"));
        Scene scene = new Scene(loader.load());
        scene.setFill(Color.TRANSPARENT);

        _STAGE_CONTROLLER_ = loader.getController();
        _STAGE_ = new CustomStage(_STAGE_CONTROLLER_);
        _ACTION_ADAPTER_ = new ActionAdapter(_STAGE_);

        _STAGE_.setScene(scene);

        ResizeHelper.addResizeListener(_STAGE_);

        _STAGE_.initStyle(StageStyle.TRANSPARENT);
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
     * Sets the title of the title-bar and changes the position (on title-bar) of the ActionButtons and title
     *
     * @param title title title for the window
     * @param buttonPos position of the buttons (whether the buttons should be on the left/right side of the title-bar)
     *                  HorizontalPos.LEFT and HorizontalPos.RIGHT are allowed here since default is HorizontalPos.LEFT,
     *                  if HorizontalPos.CENTER given, it will be ignored and the default value (LEFT) will be taken.
     * @param titlePos position of the title (of the window). The title can be placed on left/right/center of the window
     *                 as for the given HorizontalPos value.
     *
     * @return the current CustomStageBuilder object
     */
    public CustomStageBuilder setWindowTitle(String title, HorizontalPos buttonPos, HorizontalPos titlePos){
        _STAGE_CONTROLLER_.setTitle(title,buttonPos,titlePos);
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
     * @param btnMinColor color of close minimize button on hover state
     * @param btnMaxColor color of close maximize/restore button on hover state
     * @param btnCloseColor color of close button on hover state
     *
     * @return the current CustomStageBuilder object
     */
    public CustomStageBuilder setButtonHoverColor(String btnMinColor,String btnMaxColor,String btnCloseColor){
        _STAGE_CONTROLLER_.setHoverColor(btnMinColor,btnMaxColor,btnCloseColor);
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
    public CustomStageBuilder setActionIcons(Image close, Image minimize, Image maximize, Image restore){
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
     * @deprecated this method does not need to be called from this builder class after v1.1.0
     *
     * Removes the left navigation pane of the window
     *
     * @return the current CustomStageBuilder object
     *
     */
    public CustomStageBuilder removeNavigationPane(){
        _STAGE_CONTROLLER_.removeNavigationPane();
        return this;
    }


    /**
     * Sets a static navigation pane (to the pointed location) attaching the pane given
     *
     * @param type where the navigationPane should be placed on the window (LEFT/RIGHT/TOP/BOTTOM)
     * @param navigationPane root pane of the navigation (fxml file)
     * @return the current CustomStageBuilder object
     */
    public CustomStageBuilder setNavigationPane(NavigationType type, Pane navigationPane){
        _STAGE_CONTROLLER_.setNavigationPane(type,navigationPane);
        return this;
    }

    /**
     * Sets a static/dynamic navigation pane (to the pointed location) attaching the pane given
     *
     * @param style whether the navigationPane is dynamic or static
     * @param type where the navigationPane should be placed on the window (LEFT/RIGHT/TOP/BOTTOM)
     * @param navigationPane root pane of the navigation (fxml file)
     * @return the current CustomStageBuilder object
     */
    public CustomStageBuilder setNavigationPane(Style style, NavigationType type, Pane navigationPane){
        switch (style){
            case STATIC: setNavigationPane(type,navigationPane);break;
            case DYNAMIC:{
                _STAGE_CONTROLLER_.setDynamicNavigation(type, navigationPane,0,0,false);
            }
        }
        return this;
    }

    /**
     * <p>
     *     Sets the given navigationPane to the CustomStage as per its definitions (parameters).
     *     If the <b>Style</b> is <b><i>Style.STATIC</i></b> then the usual built-in static
     *     navigationPane would be generated and also, <i>verticalSpace, horizontalSpace and isSpaceDivided</i>
     *     values will be ignored.
     * </p>
     * <p>
     *     If the <b>Style</b> is <b><i>Style.STATIC</i></b> then all the values are taken and will generate a
     *     dynamic navigationPane as for its given definitions.
     * </p>
     *
     * @param style whether the navigationPane is a dynamic or static
     *
     * @param type The location where the navigationPane should be placed (top/bottom/left/right) on the window.
     *
     * @param navigationPane The root pane which should be used as the navigationPane
     *
     * @param verticalSpace This value states that, if the navigationPane is given as NavigationType.LEFT / NavigationType.RIGHT and
     *                      some space is required to be left without consuming the full height of the window (If the NavigationType
     *                      is set to be TOP/BOTTOM then this value is ignored). verticalSpace = 0 means the navigationPane will consume
     *                      the full height of the window.
     *
     * @param horizontalSpace This value states that, if the navigationPane is given as NavigationType.TOP / NavigationType.BOTTOM and
     *                        some space is required to be left without consuming the full width of the window (If the NavigationType
     *                        is set to be LEFT/RIGHT then this value is ignored). horizontalSpace = 0 means the navigationPane will consume
     *                        the full width of the window.
     *
     * @param isSpaceDivided States whether the given verticalSpace/horizontalSpace needs to be divided from top/bottom (for LEFT and RIGHT
     *                       NavigationType) or from left/right (for TOP and BOTTOM NavigationType). isSpaceDivided = false , states that
     *                       for LEFT/RIGHT NavigationType, the given verticalSpace will be allocated from the top only;
     *                       for TOP/BOTTOM NavigationType, the given horizontalSpace will only be allocated from left.
     *
     * @return the current CustomStageBuilder object
     */
    public CustomStageBuilder setNavigationPane(Style style, NavigationType type, Pane navigationPane, double verticalSpace, double horizontalSpace, boolean isSpaceDivided){
        switch (style){
            case STATIC: setNavigationPane(type,navigationPane);break;
            case DYNAMIC:{
                _STAGE_CONTROLLER_.setDynamicNavigation(type, navigationPane,verticalSpace,horizontalSpace,isSpaceDivided);
            }
        }
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