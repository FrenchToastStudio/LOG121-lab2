package log121.lab2.controller;

import log121.lab2.model.Image;
import log121.lab2.model.Perspective;
import log121.lab2.model.Position;
import log121.lab2.model.Store;

public class ModificationController extends ImageController{


    public ModificationController(Image image) {
        super(image);
    }

    // This method allows for translation on the image with the provided parameters.
    public void translate(int x, int y){
        Position position = new Position();
        position.setPosition(x,y);

        Perspective perspective = new Perspective();
        perspective.setPosition(position);

        Store.getInstance().addPerspective(perspective);
    }

    // This method allows for a zoom to occur on the image with the provided percentage.
    public void zoom(int percentage){
        Perspective perspective = new Perspective();
        perspective.setZoom(percentage);

        Store.getInstance().addPerspective(perspective);
    }

    public void undo(){

    }

    private void createMemento(){

    }
}
