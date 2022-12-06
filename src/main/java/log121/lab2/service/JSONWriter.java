package log121.lab2.service;

import log121.lab2.model.Perspective;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *  This class represents the writer that is used to write the JSON files which are used by the application
 */
public class JSONWriter {
    private static FileWriter file;
    protected static final String EXTENSION = ".json";

    protected static final String FORMAT_NAME = "json";

    protected static final LayoutFileFilter JSON_FILE_FORMAT = new LayoutFileFilter("JSON File Format", EXTENSION, true);

    /**
     * this method allows the application to save the current state
     * @param saveState to be saved
     */
    public void saveState(SaveState saveState){
        //Creer chanque objet
        JSONObject saveObject = new JSONObject();
        JSONObject imageObject = new JSONObject();
        JSONArray perspectiveArray = new JSONArray();

        //Ajouter chaque zoom et position dans perspective
        imageObject.put("path", saveState.getImage().getPath());

        saveState.getPerspectiveList().forEach(perspective ->
                {
                    JSONObject perspectiveObject = new JSONObject();
                    JSONObject positionObject = new JSONObject();

                    perspectiveObject.put("zoom", perspective.getZoom());
                    positionObject.put("x", perspective.getX());
                    positionObject.put("y", perspective.getY());
                    perspectiveObject.put("width", perspective.getWidth());
                    perspectiveObject.put("height", perspective.getHeight());
                    perspectiveObject.put("position", positionObject);

                    perspectiveArray.add(perspectiveObject);
                }
                );

        saveObject.put("Image", imageObject);
        saveObject.put("Perspectives", perspectiveArray);

        JFileChooser fileChooser = new JFileChooser();
        ExtensionFileFilter pFilter = new ExtensionFileFilter(JSON_FILE_FORMAT);
        fileChooser.setFileFilter(pFilter);
        int status = fileChooser.showSaveDialog(null);

        if (status == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                // Constructs a FileWriter given a file name, using the platform's default charset
                String fileName = selectedFile.getCanonicalPath();
                if (!fileName.endsWith(EXTENSION)) {
                    selectedFile = new File(fileName + EXTENSION);
                }
                file = new FileWriter(selectedFile);
                file.write(saveObject.toJSONString());

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    file.flush();
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    static public void ImageLog(String str) {
        System.out.println("str");
    }

}
