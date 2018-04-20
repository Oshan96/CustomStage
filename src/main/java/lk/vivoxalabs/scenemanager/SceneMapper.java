package lk.vivoxalabs.scenemanager;

import javafx.fxml.Initializable;
import javafx.scene.Node;


/**
 * Stores the view (Pane object) and its relevant controller
 *
 * Created by oshan on 18-Mar-18.
 *
 * @author oshan
 * @version 1.0
 */
public class SceneMapper<T extends Initializable, K extends Node> {

    private final T controller;
    private final K scene;

    SceneMapper(T controller, K scene) {
        this.controller = controller;
        this.scene = scene;
    }

    /**
     * @return controller
     */
    public T getController(){
        return controller;
    }


    /**
     * @return view
     */
    public K getScene(){
        return scene;
    }

}
