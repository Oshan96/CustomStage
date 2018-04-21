package lk.vivoxalabs.scenemanager.tools;

import javafx.fxml.FXMLLoader;
import lk.vivoxalabs.scenemanager.SceneManager;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to load all the files in the given package as to the given extension.
 *
 * @author oshan
 * @version 1.0
 */
public class FileLoader {

    private SceneManager manager;

    private String dir="", ext;

    /**
     * <p>
     *      IMPORTANT : <b>
     *          This DOES NOT GUARANTEE to be executed the way expected and should be avoided unless in testing.
     *          Use another constructor instead.
     *      </b>
     *      Calling this constructor will create a FileLoader object which can load the type of files of the given extension (ext)
     *      from the same package of the class the constructor was called.
     *      Ex : If the constructor was called from "Foo.class", this will create a FileLoader which is able to load the specific
     *      files from the same directory "Foo.class" file is located.
     * </p>
     *
     * <p>
     *      <b>NOTE :</b> This constructor is specifically created to be used with SceneManager. If used separately, this will not
     *      work properly and will be unable to load the files. (Since this uses getStackTrace()[3] argument and if called directly,
     *      it should probably be changed to getStackTrace()[1]).
     * </p>
     *
     * @param ext extension of the type of files needed to be loaded
     */
    public FileLoader(String ext) {
        this.ext = ext;
        try {
//            Class clazz = sun.reflect.Reflection.getCallerClass(2);
            Class clazz = Class.forName(new Throwable().getStackTrace()[3].getClassName());
            dir = new File(clazz.getProtectionDomain().getCodeSource().getLocation().getPath()).toString().replace("%20"," ");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * <p>
     *      This is same as {@link #FileLoader(String ext)}. The only difference is that this constructor will initialize the
     *      SceneManager property which can be used later (through the given SceneManager object)
     *</p>
     *
     * @param ext extension of the type of files needed to be loaded
     * @param manager SceneManager object which is going to access this FileLoader object
     */
    public FileLoader(String ext, SceneManager manager){
        this(ext);
        this.manager=manager;
    }

    /**
     * <p>
     *     This is used to make the FileLoader eligible to load the given type of files from the directory of the URL provides.
     *     Ex : {@code new FileLoader(getClass().getResource("/path/to/file/myfile.fxml")),"fxml")}
     *     Will make the FileLoader eligible to load file with ".fxml" extension from the "/path/to/file" directory.
     * </p>
     *
     * @param url URL of a class to determine the package which needs to be lookup for files to load
     * @param ext extension of the type of files needed to be loaded
     */
    public FileLoader(URL url, String ext){
        String[] content = url.toString().replace("file:/","").replace("%20"," ").split("/");

        for(int i=0; i<content.length-1;i++){
            dir+=content[i];
            if(i<content.length-2){
                dir+=File.separator;
            }
        }

        this.ext = ext;
    }

    /**
     * <p>
     *     This is same as {@link #FileLoader(URL, String)}. The only difference is that this constructor will initialize the
     *     SceneManager property which can be used later (through the given SceneManager object)
     * </p>
     *
     * @param url URL of a class to determine the package which needs to be lookup for files to load
     * @param ext extension of the type of files needed to be loaded
     * @param manager SceneManager object which is going to access this FileLoader object
     */
    public FileLoader(URL url, String ext, SceneManager manager){
        this(url, ext);
        this.manager = manager;
    }

    /**
     * <p>
     *      This is used to make the FileLoader eligible to load the given type of files from the directory given in String format.
     * </p>
     *
     * @param dir directory path
     * @param ext extension of the type of files needed to be loaded
     */
    public FileLoader(String dir, String ext) {
        this.dir=dir;
        this.ext=ext;
    }

    /**
     * See {@link #FileLoader(String, String, SceneManager)}
     *
     * @param dir directory path
     * @param ext extension of the type of files needed to be loaded
     * @param manager SceneManager object which is going to access this FileLoader object
     */
    public FileLoader(String dir, String ext, SceneManager manager){
        this(dir, ext);
        this.manager=manager;
    }

    /**
     * Collects the set of files with the given extension and inside the given directory for the FileLoader.
     *
     * @return the loaded available files.
     */
    public List<File> collect(){

        List<File> files = new ArrayList<>();
        String list[];

        File folder = new File(dir);

        if(!folder.isDirectory()){
            System.out.println("Not a directory");
            return files;
        }

        if(ext.equalsIgnoreCase("fxml")) {
            list = folder.list(new FXMLExtensionFilter());
        }else{
            list = folder.list(new ExtensionFilter());
        }

        for(String name : list){
            files.add(new File(dir + File.separator + name));
        }

        return files;
    }

    private class FXMLExtensionFilter implements FilenameFilter {
        @Override
        public boolean accept(File dir, String name) {
            if(name.endsWith(ext)) {
                try {
                    if(manager!=null) {
                        FXMLLoader loader = new FXMLLoader(new File(dir.toString().concat(File.separator).concat(name)).toURI().toURL());
                        manager.addScene(name.replace("." + ext, ""), loader.load(), loader.getController());
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }catch (Exception e){
//                    e.printStackTrace();
                }

                return true;
            }
            return false;
        }
    }

    private class ExtensionFilter implements FilenameFilter{
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(ext);
        }
    }
}