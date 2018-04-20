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
 * @author oshan
 */
public class FileLoader {

    private SceneManager manager;

    private String dir="", ext;

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

    public FileLoader(String ext, SceneManager manager){
        this(ext);
        this.manager=manager;
    }

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

    public FileLoader(URL url, String ext, SceneManager manager){
        this(url, ext);
        this.manager = manager;
    }

    public FileLoader(String dir, String ext) {
        this.dir=dir;
        this.ext=ext;
    }

    public FileLoader(String dir, String ext, SceneManager manager){
        this(dir, ext);
        this.manager=manager;
    }

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