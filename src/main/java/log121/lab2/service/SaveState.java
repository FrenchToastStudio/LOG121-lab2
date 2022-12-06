package log121.lab2.service;

import log121.lab2.model.Image;
import log121.lab2.model.Perspective;

import java.util.List;

/**
 * This class represents a save state that is used in the load and save system by the application
 */
public class SaveState {
    private List<Perspective> perspectiveList;
    private Image image;

    public SaveState(){}

    /**
     * this constructor allows to create a saveState object with the specified perspectiveList and image
     * @param perspectiveList to be used
     * @param image to be used
     */
    public SaveState(List<Perspective> perspectiveList, Image image){
        this.perspectiveList = perspectiveList;
        this.image = image;
    }

    /**
     * this method allows to retrieve the perspectiveList variable
     * @return the perspective list
     */
    public List<Perspective> getPerspectiveList() {
        return perspectiveList;
    }

    /**
     * this method allows to retrieve the image variable
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * this method allows to set the perspectiveList variable to a specified one
     * @param perspectiveList to be set
     */
    public void setPerspectiveList(List<Perspective> perspectiveList) {
        this.perspectiveList = perspectiveList;
    }

    /**
     *  this method allows to set the image variable to a specified one
     * @param image to be set
     */
    public void setImage(Image image) {
        this.image = image;
    }
}
