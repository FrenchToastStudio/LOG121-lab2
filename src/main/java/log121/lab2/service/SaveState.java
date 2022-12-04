package log121.lab2.service;

import log121.lab2.model.Image;
import log121.lab2.model.Perspective;

import java.util.List;

public class SaveState {
    private List<Perspective> perspectiveList;
    private Image image;

    public SaveState(){}

    public SaveState(List<Perspective> perspectiveList, Image image){
        this.perspectiveList = perspectiveList;
        this.image = image;
    }

    public List<Perspective> getPerspectiveList() {
        return perspectiveList;
    }

    public Image getImage() {
        return image;
    }

    public void setPerspectiveList(List<Perspective> perspectiveList) {
        this.perspectiveList = perspectiveList;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
