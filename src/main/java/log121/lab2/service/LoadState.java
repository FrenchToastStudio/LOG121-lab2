package log121.lab2.service;

import log121.lab2.model.Image;
import log121.lab2.model.Perspective;
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

public class LoadState {
    private List<Perspective> perspectiveList;
    private Image image;

    public void load(){
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Selectionnez un fichier de configuration");
        fileChooser.setAcceptAllFileFilterUsed(false);


        FileNameExtensionFilter filtre = new FileNameExtensionFilter(".json", "json");
        fileChooser.addChoosableFileFilter(filtre);

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());
            parse(selectedFile);
        }


    }

    public void parse(File file){
        JSONParser parser = new JSONParser();

        try {
            JSONObject a = (JSONObject) parser.parse(new FileReader(file.getAbsolutePath()));
            JSONArray perspectives = (JSONArray) a.get("Perspectives");
            JSONObject imageObject = (JSONObject) a.get("Image");

            //Gerer les perspectives
            List<Perspective> perspectiveList = new ArrayList<>();
            for (int i=0; i < perspectives.size(); i++){
                //gerer une perspective
                JSONObject perspective = (JSONObject) perspectives.get(i);

                //gerer le zoom
                System.out.println("Perspective " + i + " :");
                long zoom = (long) perspective.get("zoom");
                System.out.println("    zoom: " + zoom);

                //gerer la position
                JSONObject position = (JSONObject) perspective.get("position");
                long x = (long) position.get("x");
                System.out.println("    x: " + x);
                long y = (long) position.get("y");
                System.out.println("    y: " +y);

            }

            //Gerer l'image
            String pathImage = (String) imageObject.get("image");
            Image image = new Image(pathImage);
            System.out.println(pathImage.toString());

            //setter le store
            Store.getInstance().setImage(image);
            Store.getInstance().setPerspectives(perspectiveList);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

}
