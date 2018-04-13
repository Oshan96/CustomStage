package lk.vivoxalabs.customdrawer;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import lk.vivoxalabs.customdrawer.tools.DrawerEvent;
import lk.vivoxalabs.customstage.tools.NavigationType;

/**
 * A drawer which can be shown/hidden as user calls
 *
 * @author oshan
 * @version 1.0.0
 */
public class CustomDrawer extends StackPane {

    private TranslateTransition transition;

    private DrawerEvent drawerEvent;

    private boolean isShown=true;

    {
        setPadding(new Insets(1));
        getStylesheets().add(getClass().getResource("/css/customstage.css").toExternalForm());
    }

    /**
     * @param navigationType the position of the navigationPane (top/bottom/left/right)
     */
    public CustomDrawer(NavigationType navigationType){
        transition = new DrawerTransition(this).setTransition();
        setDrawerEvent(navigationType);
    }

    /**
     * @param navigationType the position of the navigationPane (top/bottom/left/right)
     * @param millis how much time the transition should take on drawer.hide() and drawer.open()
     */
    public CustomDrawer(NavigationType navigationType, Duration millis){
        transition = new DrawerTransition(this).setTransition(millis);
        setDrawerEvent(navigationType);
    }


    private void setDrawerEvent(NavigationType navigationType){
        switch (navigationType){
            case LEFT:{
                drawerEvent=new LeftDrawerEvent();
                getStyleClass().add("left-navigation");
            }break;
            case RIGHT:{
                drawerEvent=new RightDrawerEvent();
                getStyleClass().add("right-navigation");
            }break;
            case TOP:{
                drawerEvent=new TopDrawerEvent();
                getStyleClass().add("top-navigation");
            }break;
            case BOTTOM:{
                drawerEvent=new BottomDrawerEvent();
                getStyleClass().add("bottom-navigation");
            }break;

            default:drawerEvent=null;
        }
    }

    /**
     * Registers the given Pane as the drawer's root
     *
     * @param root Pane which is used as the drawer
     */
    public void registerRoot(Pane root){
        this.getChildren().setAll(root);
    }

    /**
     * Clears the content of the drawer (This method by-default is called on a FXThread)
     */
    public void unregisterRoot(){
        Platform.runLater(()-> this.getChildren().clear());
    }

    /**
     * Opens the drawer
     */
    public void open(){
        drawerEvent.open();
        isShown=true;
    }

    /**
     * Closes the drawer
     */
    public void hide(){
        drawerEvent.hide();
        isShown=false;
    }

    /**
     * @return Whether the drawer is currently open (true) or not (false)
     */
    public boolean isShown(){
        return isShown;
    }

    private class DrawerTransition{

        private CustomDrawer drawer;

        DrawerTransition(CustomDrawer drawer){
            this.drawer=drawer;
        }

        TranslateTransition setTransition(){
            TranslateTransition transition=new TranslateTransition(Duration.millis(300),drawer);
            transition.setCycleCount(1);
            return transition;
        }

        TranslateTransition setTransition(Duration millis){
            TranslateTransition transition=new TranslateTransition(millis,drawer);
            transition.setCycleCount(1);
            return transition;
        }
    }

    private class LeftDrawerEvent implements DrawerEvent{

        @Override
        public void open() {
            transition.setToX(0);
            transition.play();
        }

        @Override
        public void hide() {
            transition.setToX(-getWidth());
            transition.play();
        }
    }

    private class RightDrawerEvent implements DrawerEvent{
        @Override
        public void open() {
            transition.setToX(0);
            transition.play();
        }
        @Override
        public void hide() {
            transition.setToX(getWidth());
            transition.play();
        }
    }

    private class TopDrawerEvent implements DrawerEvent{
        @Override
        public void open() {
            transition.setToY(0);
            transition.play();
        }
        @Override
        public void hide() {
            transition.setToY(-getHeight());
            transition.play();
        }
    }

    private class BottomDrawerEvent implements DrawerEvent{
        @Override
        public void open() {
            transition.setToY(0);
            transition.play();
        }
        @Override
        public void hide() {
            transition.setToY(getHeight());
            transition.play();
        }
    }

}
