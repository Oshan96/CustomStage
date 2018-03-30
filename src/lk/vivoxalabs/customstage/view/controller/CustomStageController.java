package lk.vivoxalabs.customstage.view.controller;

import com.sun.istack.internal.Nullable;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.vivoxalabs.customstage.tools.ActionAdapter;
import lk.vivoxalabs.customstage.tools.NavigationType;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class of the CustomStage (fxml file) and is responsible for the behaviour of the CustomStage
 *
 * @author oshan
 * @version 1.1.0
 */
public class CustomStageController implements Initializable {

    private ActionAdapter actionAdapter;

    private double offsetX;
    private double offsetY,screenY;

    private Image imgMaximize,imgRestore;

    private boolean isDim =false;
    private Stage dimStage;

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

    public CustomStageController(){
        imgMaximize = new Image("/lk/vivoxalabs/customstage/util/icons/maximize.png");
        imgRestore = new Image("/lk/vivoxalabs/customstage/util/icons/restore.png");

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
                root.setStyle(root.getStyle()+"window-color:"+color+";");
            }break;
            case TITLE_TEXT_FILL:{
                lblTitle.setStyle("-fx-text-fill:"+color+";");
            }break;
            case BUTTON_HOVER_COLOR:{
                root.setStyle(root.getStyle()+"button-hover-color:"+color+";");
            }break;
            case BUTTON_COLOR:{
                root.setStyle(root.getStyle()+"button-color:"+color+";");
            }
        }
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
     * Removes the pointed navigationPane from the window
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
    public void setActionIcons(@Nullable Image close,@Nullable Image minimize,@Nullable Image maximize,@Nullable Image restore){
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleBar.setOnMousePressed(event -> {
            offsetX=event.getSceneX();
            offsetY=event.getSceneY();
        });

        titleBar.setOnMouseDragged(event -> {
            screenY=event.getScreenY()-offsetY;

            if(screenY<0 && !isDim){
                isDim =true;
                dimStage.show();
            }else if(screenY > 0){
                dimStage.hide();
                isDim=false;
                System.gc();
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
                System.out.println(event.getClickCount());
                maximizeRestore(event);
            }
        });

        containerPane.getChildren().remove(containerPane.leftProperty().get());
        containerPane.getChildren().remove(containerPane.rightProperty().get());
        containerPane.getChildren().remove(containerPane.topProperty().get());
        containerPane.getChildren().remove(containerPane.bottomProperty().get());
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

}
