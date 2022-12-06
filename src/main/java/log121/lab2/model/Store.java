package log121.lab2.model;

import java.util.ArrayList;
import java.util.List;

/**
 *  This model represents the store class, where the image and its perspectives are kept
 */
public class Store {
    private Image image;
    private List<Perspective> perspectives;

    private static Store instance;

    public Store()
    {
        this.perspectives = new ArrayList<>();
    }

    /**
     * get the instance of this store
     * @return this instance
     */
    public static Store getInstance()
    {
        if(instance == null) {
            instance = new Store();
        }
        return  instance;
    }

    /**
     * get the image variable
     * @return the variable image
     */
    public Image getImage() {
        return image;
    }

    /**
     * set the image variable
     * @param image
     */
    public void setImage(Image image) {
        this.image = image;
        this.image.notifyObserversPathChanged();
        if (this.perspectives != null)
            image.setPerspective(this.perspectives);
    }

    /**
     * get the perspective variable list
     * @return the perspective list
     */
    public List<Perspective> getPerspectives() {
        return perspectives;
    }

    /**
     * this method allowes  to assign the perspective list variable to specified list of perspectives
     * @param perspectives the list of perspectives
     */
    public void setPerspectives(List<Perspective> perspectives) {
        if(this.image != null) {
            this.image.setPerspective(perspectives);
        }
        this.perspectives = perspectives;
        this.perspectives.forEach(Perspective::notifyObserverObjectChanged);
    }

    /**
     * this method allows  to add a perspective to the variable list of perspectives
     * @param perspective to add
     */
    public void addPerspective(Perspective perspective)
    {
        perspective.setId(perspectives.size());
        this.perspectives.add(perspective);
    }

    /**
     * this method allow to reset the variable list of perspectives
     */
    public void resetPerspective()
    {
        this.image.perspectives = new ArrayList<>();
        this.perspectives = new ArrayList<>();
    }
}
