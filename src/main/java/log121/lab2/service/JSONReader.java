package log121.lab2.service;

import log121.lab2.model.Image;
import log121.lab2.model.Perspective;
import log121.lab2.model.Position;
import log121.lab2.model.Store;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JSONReader {
    private List<Perspective> perspectiveList;
    private Image image;

    public SaveState load(){
        SaveState saveState = new SaveState();

        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Selectionnez un fichier de configuration");
        fileChooser.setAcceptAllFileFilterUsed(false);


        FileNameExtensionFilter filtre = new FileNameExtensionFilter(".json", "json");
        fileChooser.addChoosableFileFilter(filtre);

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());
            saveState = parse(selectedFile);
        }

        return saveState;

    }

    public SaveState parse(File file){
        JSONParser parser = new JSONParser();
        SaveState saveState = new SaveState();
        try {
            JSONObject a = (JSONObject) parser.parse(new FileReader(file.getAbsolutePath()));
            JSONArray perspectives = (JSONArray) a.get("Perspectives");
            JSONObject imageObject = (JSONObject) a.get("Image");

            //Gerer les perspectives
            List<Perspective> perspectiveList = new ArrayList<>();
            for (Object perspectiveObject: perspectives){
                //gerer une perspective
                JSONObject perspective = (JSONObject) perspectiveObject;

                //gerer le zoom
                long zoom = (long) perspective.get("zoom");
                //gerer la position
                JSONObject position = (JSONObject) perspective.get("position");

                long x = (long) position.get("x");
                long y = (long) position.get("y");

                long width = (long) perspective.get("width");

                long height = (long) perspective.get("height");

                perspectiveList.add(
                    new Perspective()
                            .setWidth((int)width)
                            .setHeight((int)height)
                            .setPosition(new Position((int)x, (int)y))
                );
            }

            //Gerer l'image
            String pathImage = (String) imageObject.get("path");
            Image image = new Image();
            image.setPath(pathImage);
            image.setPerspective(perspectiveList);
            System.out.println(pathImage.toString());

            saveState.setImage(image);
            saveState.setPerspectiveList(perspectiveList);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return saveState;
    }

}
