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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.vivoxalabs.customstage.CustomStageBuilder;
import lk.vivoxalabs.customstage.tools.ActionAdapter;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by oshan on 08-Mar-18.
 *
 * @author oshan
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
    private StackPane dynamicPane,navigationPane;
    @FXML
    private Label lblTitle;

    public CustomStageController(){
        imgMaximize = new Image("/lk/vivoxalabs/customstage/util/icons/maximize.png");
        imgRestore = new Image("/lk/vivoxalabs/customstage/util/icons/restore.png");


        AnchorPane p = new AnchorPane();
        p.setPrefHeight(CustomStageBuilder.DIMENSION.getHeight()-60);
        p.setPrefWidth(CustomStageBuilder.DIMENSION.getWidth()-10);
        p.setStyle("-fx-background-color: rgba(54,23,120,0.2);");
        dimStage= new Stage();
        dimStage.setScene(new Scene(p));
        dimStage.initStyle(StageStyle.TRANSPARENT);
        dimStage.getScene().setFill(Color.TRANSPARENT);
        dimStage.setAlwaysOnTop(false);
    }


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

    public void setTitle(String title){
        lblTitle.setText(title);
    }

    public void setActionAdapter(ActionAdapter actionAdapter){
        this.actionAdapter=actionAdapter;
    }

    public void changeScene(Pane pane){
        Platform.runLater(()->{
            dynamicPane.getChildren().clear();
            dynamicPane.getChildren().add(pane);
        });
    }

    public void setStyleSheet(URL path) {
        root.getStylesheets().add(path.toExternalForm());
    }

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

    public void setNavigationPane(Pane navigationPane){
            this.navigationPane.getChildren().clear();
            this.navigationPane.getChildren().add(navigationPane);
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
