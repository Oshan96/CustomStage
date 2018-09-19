package lk.vivoxalabs.customstage.view.controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.vivoxalabs.customdrawer.CustomDrawer;
import lk.vivoxalabs.customstage.tools.ActionAdapter;
import lk.vivoxalabs.customstage.tools.HorizontalPos;
import lk.vivoxalabs.customstage.tools.NavigationType;

import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Controller class of the CustomStage (fxml file) and is responsible for the behaviour of the CustomStage
 *
 * @author oshan
 * @version 1.1.1
 */
public class CustomStageController implements Initializable {

    private ActionAdapter actionAdapter;

    private double offsetX;
    private double offsetY,screenY;

    private Image imgMaximize,imgRestore;

    private boolean isDim=false;

    private Stage dimStage;

    private Map<NavigationType,CustomDrawer> dynamicDrawers=new HashMap<>();

    private ButtonRightPositionedEvent defaultPositions;

    @FXML
    private AnchorPane titleBar,root;
    @FXML
    private Button btnMax,btnClose,btnMin;
    @FXML
    private StackPane dynamicPane, left_navigationPane,right_navigationPane,top_navigationPane,bottom_navigationPane;
    @FXML
    private BorderPane containerPane;
    @FXML
    private Label lblTitle;
    @FXML
    private HBox btnContainer;

    public CustomStageController(){
        imgMaximize = new Image("/util/icons/maximize.png");
        imgRestore = new Image("/util/icons/restore.png");

        Rectangle rec = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

        AnchorPane p = new AnchorPane();
        p.setPrefHeight(rec.height-10);
        p.setPrefWidth(rec.width-10);
        p.setStyle("-fx-background-color: rgba(54,23,120,0.2);");
        dimStage= new Stage();
        dimStage.setScene(new Scene(p));
        dimStage.initStyle(StageStyle.TRANSPARENT);
        dimStage.getScene().setFill(Color.TRANSPARENT);
        dimStage.setAlwaysOnTop(false);


    }


    /**
     * Changes the color of the given component of the window
     *
     * @param component the window component to be styled
     * @param color name/hex/rgb/rgba value of the color
     */
    public void setStyle(StageComponent component, String color){
        switch (component){
            case WINDOW_COLOR:{
                String style = root.getStyle();
                if(style.matches("window-color:.*;")){
                    style=style.replaceAll("window-color:.*;","");
                }

                root.setStyle(style+"window-color:"+color+";");

            }break;
            case TITLE_TEXT_FILL:{
                lblTitle.setStyle("-fx-text-fill:"+color+";");
            }break;
            case BUTTON_HOVER_COLOR:{
                if(root.getStyle().contains("button-hover-color")){
                    root.setStyle(root.getStyle().replaceAll("button-hover-color:.*;","button-hover-color:"+color+";"));
                }else{
                    root.setStyle(root.getStyle()+"button-hover-color:"+color+";");
                }
            }break;
            case BUTTON_COLOR:{
                if(root.getStyle().contains("button-color")){
                    root.setStyle(root.getStyle().replaceAll("button-color:.*;","button-color:"+color+";"));
                }else {
                    root.setStyle(root.getStyle() + "button-color:" + color + ";");
                }
            }
        }
    }

    /**
     * Sets different colors for button on mouse hover state
     *
     * @param btnMinColor color of close minimize button on hover state
     * @param btnMaxColor color of close maximize/restore button on hover state
     * @param btnCloseColor color of close button on hover state
     */
    public void setHoverColor(String btnMinColor,String btnMaxColor, String btnCloseColor){
        root.setStyle(
                root.getStyle()+
                "button-close-hover-color:"+btnCloseColor+";"+
                "button-max-hover-color:"+btnMaxColor+";"+
                "button-hover-color:"+btnMinColor+";"
        );

    }

    /**
     * Sets the title of the title-bar
     *
     * @param title title for the window
     */
    public void setTitle(String title){
        lblTitle.setText(title);
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
     */
    public void setTitle(String title, HorizontalPos buttonPos, HorizontalPos titlePos){
        setTitle(title);
        if(buttonPos!=HorizontalPos.LEFT && titlePos==HorizontalPos.LEFT){
            return;
        }

        titleBar.widthProperty().removeListener(defaultPositions);

        switch (buttonPos){
            case LEFT:{
                titleBar.widthProperty().addListener(new ButtonLeftPositionedEvent(titlePos));
            }break;
            default:{
                titleBar.widthProperty().addListener(new ButtonRightPositionedEvent(titlePos));
            }
        }
    }

    /**
     * @param actionAdapter ActionAdapter object to control close,maximize/restore,minimize actions
     */
    public void setActionAdapter(ActionAdapter actionAdapter){
        this.actionAdapter=actionAdapter;
    }

    /**
     * Changes the current view of the Stage to the given view (pane)
     *
     * @param pane root pane of the loaded fxml view
     */
    public void changeScene(Pane pane){
        Platform.runLater(()->{
            dynamicPane.getChildren().clear();
            dynamicPane.getChildren().add(pane);
        });
    }

    /**
     * Style the CustomStage as to the user given stylesheet
     *
     * @param path URL of the stylesheet
     */
    public void setStyleSheet(URL path) {
        root.getStylesheets().add(path.toExternalForm());
    }

    /**
     * @deprecated use removeNavigationPane(NavigationType type) method instead
     *
     * Removes the left navigation pane of the window
     */
    public void removeNavigationPane(){
        containerPane.getChildren().remove(containerPane.leftProperty().get());
    }

    /**
     * Removes the pointed static navigationPane from the window (does not work for dynamic navigationPanes)
     *
     * @param type which navigationPane should be removed from the window (LEFT/RIGHT/TOP/BOTTOM)
     */
    public void removeNavigationPane(NavigationType type){
        switch (type){
            case LEFT:{
                containerPane.getChildren().remove(left_navigationPane);
            }break;
            case RIGHT:{
                containerPane.getChildren().remove(right_navigationPane);
            }case TOP:{
                containerPane.getChildren().remove(containerPane.topProperty().get());
            }break;
            case BOTTOM:{
                containerPane.getChildren().remove(containerPane.bottomProperty().get());
            }
        }
    }

    /**
     * Changes the default icons for the action buttons on Title-bar
     *
     * @param close Icon for close button
     * @param minimize Icon for minimize button
     * @param maximize Window maximize (maximize button) icon
     * @param restore Window restore (maximize button) icon
     */
    public void setActionIcons(Image close,Image minimize,Image maximize,Image restore){
        if(close!=null){
            Platform.runLater(()-> btnClose.setGraphic(new ImageView(close)));
        }
        if(minimize!=null){
            Platform.runLater(()-> btnMin.setGraphic(new ImageView(minimize)));
        }
        if(maximize!=null){
            Platform.runLater(()-> btnMax.setGraphic(new ImageView(maximize)));
            imgMaximize=maximize;
        }
        if(restore!=null){
            imgRestore=restore;
        }
    }

    /**
     * Sets a static navigation pane (right side of the window) attaching the pane given
     *
     * @param type where the navigationPane should be placed on the window (LEFT/RIGHT/TOP/BOTTOM)
     * @param navigationPane root pane of the navigation (fxml file)
     */
    public void setNavigationPane(NavigationType type, Pane navigationPane){
        switch (type){
            case LEFT:{
                this.left_navigationPane.getChildren().clear();
                this.left_navigationPane.getChildren().add(navigationPane);
                containerPane.setLeft(left_navigationPane);
            }break;
            case RIGHT:{
                this.right_navigationPane.getChildren().clear();
                this.right_navigationPane.getChildren().add(navigationPane);
                containerPane.setRight(right_navigationPane);
            }break;
            case TOP:{
                this.top_navigationPane.getChildren().clear();
                this.top_navigationPane.getChildren().add(navigationPane);
                containerPane.setTop(top_navigationPane);
            }break;
            case BOTTOM:{
                this.bottom_navigationPane.getChildren().clear();
                this.bottom_navigationPane.getChildren().add(navigationPane);
                containerPane.setBottom(bottom_navigationPane);
            }
        }
    }


    /**
     * <p>Sets the given navigationPane to the CustomStage as per its definitions (parameters).</p>
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
     */
    public void setDynamicNavigation(NavigationType type,Pane navigationPane,double verticalSpace,double horizontalSpace,boolean isSpaceDivided){
        CustomDrawer drawer = new CustomDrawer(type);
        drawer.registerRoot(navigationPane);
        switch (type){
            case LEFT:{
                drawer.prefWidthProperty().bind(left_navigationPane.prefWidthProperty());
                if(isSpaceDivided) {
                    drawer.setLayoutY(31 + (verticalSpace / 2));
                }else {
                    drawer.setLayoutY(31 + verticalSpace);
                }
                root.heightProperty().addListener((observable, oldValue, newValue) -> {
                    drawer.setPrefHeight(newValue.doubleValue()-(titleBar.getPrefHeight()+2+verticalSpace));
                });
            }break;
            case RIGHT:{
                drawer.prefWidthProperty().bind(right_navigationPane.prefWidthProperty());
                if(isSpaceDivided) {
                    drawer.setLayoutY(31 + (verticalSpace / 2));
                }else {
                    drawer.setLayoutY(31+verticalSpace);
                }
                root.widthProperty().addListener((observable, oldValue, newValue) -> {
                    drawer.setLayoutX(newValue.doubleValue()-drawer.getWidth());
                });
                root.heightProperty().addListener((observable, oldValue, newValue) -> {
                    drawer.setPrefHeight(newValue.doubleValue()-(titleBar.getPrefHeight()+2+verticalSpace));
                });
            }break;
            case TOP:{
                drawer.prefHeightProperty().bind(top_navigationPane.prefHeightProperty());
                drawer.setLayoutY(31);
                if(isSpaceDivided) {
                    drawer.setLayoutX(horizontalSpace / 2);
                }else {
                    drawer.setLayoutX(horizontalSpace);
                }
                root.widthProperty().addListener((observable, oldValue, newValue) -> {
                    drawer.setPrefWidth(newValue.doubleValue()-horizontalSpace);
                });
            }break;
            case BOTTOM:{
                drawer.prefHeightProperty().bind(bottom_navigationPane.prefHeightProperty());
                if(isSpaceDivided){
                    drawer.setLayoutX(horizontalSpace/2);
                }else {
                    drawer.setLayoutX(horizontalSpace);
                }
                root.widthProperty().addListener((observable, oldValue, newValue) -> {
                    drawer.setPrefWidth(newValue.doubleValue()-horizontalSpace);
                });
                root.heightProperty().addListener((observable, oldValue, newValue) -> {
                    drawer.setLayoutY(newValue.doubleValue()-(drawer.getHeight()));
                });
            }
        }

        root.getChildren().add(drawer);

        dynamicDrawers.put(type,drawer);
    }

    /**
     * This method shall be called if a dynamic navigationPane is in use.
     * Will call either drawer.open() or drawer.hide() method depending on the drawer's current showing state.
     *
     * @param type the navigationPane which the event should be triggered for
     */
    public void dynamicDrawerEvent(NavigationType type){
        CustomDrawer drawer = dynamicDrawers.get(type);
        if (drawer.isShown()) {
            titleBar.toFront();
            drawer.hide();
        } else {
            drawer.open();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        defaultPositions = new ButtonRightPositionedEvent(HorizontalPos.LEFT);

        titleBar.setOnMousePressed(event -> {
            offsetX=event.getSceneX();
            offsetY=event.getSceneY();
        });

        titleBar.setOnMouseDragged(event -> {
            screenY=event.getScreenY()-offsetY;

            if(screenY<0 && !isDim){
                isDim =true;
                dimStage.show();
                ((Stage)((Node)event.getSource()).getScene().getWindow()).toFront();
            }else if(screenY > 0){
                dimStage.hide();
                isDim=false;
            }

            if((((Stage)((Node)event.getSource()).getScene().getWindow()).isMaximized())) {
                maximizeRestore(event);
            }

            (titleBar.getScene().getWindow()).setX(event.getScreenX()-offsetX);
            (titleBar.getScene().getWindow()).setY(screenY);

        });

        titleBar.setOnMouseReleased(event -> {
            if(screenY<0){
                maximizeRestore(event);
                dimStage.hide();
                isDim=false;
            }
        });

        titleBar.setOnMouseClicked(event -> {
            if(event.getClickCount()==2){
                maximizeRestore(event);
            }
        });

        containerPane.getChildren().remove(containerPane.leftProperty().get());
        containerPane.getChildren().remove(containerPane.rightProperty().get());
        containerPane.getChildren().remove(containerPane.topProperty().get());
        containerPane.getChildren().remove(containerPane.bottomProperty().get());

        titleBar.widthProperty().addListener(defaultPositions);

    }


    @FXML
    private void close(){
        actionAdapter.close();
    }

    @FXML
    private void minimize(){
        actionAdapter.minimize();
    }

    @FXML
    private void maximizeRestore(MouseEvent evt){
        Stage stage = ((Stage)((Node)evt.getSource()).getScene().getWindow());
        if(stage.isMaximized()){
            actionAdapter.restore();
            if(root.getScene().getWindow().getY()<0){
                root.getScene().getWindow().setY(0);
            }
            btnMax.setGraphic(new ImageView(imgMaximize));
            btnMax.getTooltip().setText("Maximize");
        }else{
            actionAdapter.maximize();
            btnMax.setGraphic(new ImageView(imgRestore));
            btnMax.getTooltip().setText("Restore Down");
        }
    }

    public enum StageComponent{
        TITLE_TEXT_FILL,WINDOW_COLOR,BUTTON_HOVER_COLOR,BUTTON_COLOR
    }

    private class ButtonRightPositionedEvent implements ChangeListener<Number>{

        ButtonRightPositionedEvent(HorizontalPos titlePos){
            switch (titlePos){
                case CENTER: {
                    lblTitle.setAlignment(Pos.CENTER);
                    titleBar.widthProperty().addListener((observable, oldValue, newValue) -> {
                        lblTitle.setLayoutX((newValue.doubleValue()/2)-(lblTitle.getWidth()/2));
                    });
                }break;
                default: {
                    titleBar.widthProperty().addListener((observable, oldValue, newValue) -> {
                        lblTitle.setLayoutX(5);
                    });
                }
            }
        }

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            btnContainer.setLayoutX(newValue.doubleValue()-146);
        }
    }

    private class ButtonLeftPositionedEvent implements ChangeListener<Number>{

        ButtonLeftPositionedEvent(HorizontalPos titlePos){

            btnContainer.getChildren().clear();
            btnContainer.getChildren().add(0,btnClose);
            btnContainer.getChildren().add(1,btnMin);
            btnContainer.getChildren().add(2,btnMax);

            lblTitle.setAlignment(Pos.CENTER_RIGHT);

            switch (titlePos){
                case CENTER: {
                    lblTitle.setAlignment(Pos.CENTER);
                    titleBar.widthProperty().addListener((observable, oldValue, newValue) -> {
                        lblTitle.setLayoutX((newValue.doubleValue()/2)-(lblTitle.getWidth()/2));
                    });
                }break;

                default: {
                    titleBar.widthProperty().addListener((observable, oldValue, newValue) -> {
                        lblTitle.setLayoutX(newValue.doubleValue()-lblTitle.getWidth()-5);
                    });
                }
            }
        }

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            btnContainer.setLayoutX(2);
        }

    }

}
