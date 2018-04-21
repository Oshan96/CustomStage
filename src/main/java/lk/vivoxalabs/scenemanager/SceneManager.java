package lk.vivoxalabs.scenemanager;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import lk.vivoxalabs.scenemanager.tools.FileLoader;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * This SceneManager is used to manage the views (which are changing)
 * by loading new views on to the scene.
 * Each new view consists of a .fxml file and a Controller class for that
 * So, this SceneManager can be used to store those Controllers and their relevant views (Panes)
 * and access them individually when needed.
 * This reduces the issues when you have to communicate between different views and having to create
 * new views (using FXMLLoader) each time needed.
 * Load a view and controller once, and store it in SceneManager object.
 * Then that SceneManager object can store all the stage's controllers and views and can be retrieved later when needed.
 *
 * Created by oshan on 18-Mar-18.
 *
 * @author oshan
 * @version 1.0
 */
public class SceneManager {

    private final Map<String,SceneMapper> sceneMap;

    private FileLoader fileLoader;

    public SceneManager() {
        sceneMap = new HashMap<>();
    }

    /**
     * Adds the specific view and its controller to the map.
     *
     * @param id name which you will access this scene later on
     * @param controller controller of the specific view (this must implement Initializable)
     * @param scene parent node retrieved from fxmlLoader.load()
     * @param <T> Class of the Controller (extends Initializable)
     * @param <K> Class of the view (extends Node)
     */
    public <T extends Initializable,K extends Node> void addScene(String id, K scene, T controller){
        sceneMap.put(
          id,
          new SceneMapper<T,K>(
                  controller,
                  scene
          )
        );
    }

    /**
     * @param id id which is used to map the scene
     * @param <K> Class of the view (extends Node)
     * @return the view (Pane) node
     */
    public <K extends Node> K getScene(String id){
        return (K) sceneMap.get(id).getScene();
    }

    /**
     * @param id id which is used to map the scene
     * @param <T> Class of the view (extends Node)
     * @return the controller of the specific view
     */
    public <T extends Initializable> T getController(String id ){
        return (T) sceneMap.get(id).getController();
    }

    /**
     * @return all the id which are used to map views stored
     */
    public String[] getIds(){
        return sceneMap.keySet().toArray(new String[]{});
    }

    /**
     * @deprecated Use another "automate" method instead. This does not work unless used with a SceneManager object you have created.
     * (Does not work with the defaultSceneManager object provided). <br>
     *
     * <b>NOTE : {@code CustomStage.getDefaultSceneManager().automate();} will not work </b>
     *
     * <p>
     *     When called gets the directory to collect files as the directory of the caller class.
     *     Ex : If "Foo.class" calls this method, then fxml files available in the same directory as "Foo.class" will be loaded.
     * </p>
     *
     */
    public void automate(){
        fileLoader = new FileLoader("fxml",this);
        fileLoader.collect();
    }

    /**
     * <p>
     *      This is used to make the FileLoader eligible to load the given type of files from the directory given in String format.
     * </p>
     *
     * @param dir directory path for fxml files to be loaded from
     */
    public void automate(String dir){
        fileLoader = new FileLoader(dir,"fxml",this);
        fileLoader.collect();
    }

    /**
     * <p>
     *     This is used to make the FileLoader eligible to load the given type of files from the directory of the URL provides.
     *     Ex : {@code sceneManager.automate(getClass().getResource("/path/to/file/myfile.fxml")))}
     *     Will make the FileLoader eligible to load file with ".fxml" extension from the "/path/to/file" directory.
     * </p>
     * @param url URL of a class to determine the package which needs to be lookup for fxml files to load
     */
    public void automate(URL url){
        fileLoader = new FileLoader(url,"fxml",this);
        fileLoader.collect();
    }
}
