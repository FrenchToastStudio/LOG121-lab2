package log121.lab2.controller;

import log121.lab2.model.Image;
import log121.lab2.model.JsonWriter;
import log121.lab2.view.ImageView;

import java.util.List;

public class MainController {
    private Image image;
    private List<ImageView> views;
    private int activeView;

    public void changeView(){

    }

    public void save(){
        JsonWriter jsonWriter = new JsonWriter();
        jsonWriter.saveState(this.image, this.views, this.activeView);
    }


}
