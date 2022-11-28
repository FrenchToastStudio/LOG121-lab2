package log121.lab2.controller;

import log121.lab2.model.Image;

public class ModificationController extends ImageController{


    public ModificationController(Image image) {
        super(image);
    }

    // This method allows for translation on the image with the provided parameters.
    public void translate(int x, int y){

    }

    // This method allows for a zoom to occur on the image with the providede percentage.
    public void zoom(int percentage){

    }

    public void undo(){

    }

    private void createMemento(){

    }
}
