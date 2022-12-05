package log121.lab2.model;

import log121.lab2.controller.CommandManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private Image image;
    private List<Perspective> perspectives;

    private static Store instance;

    public Store()
    {
        this.perspectives = new ArrayList<>();
    }

    public static Store getInstance()
    {
        if(instance == null) {
            instance = new Store();
        }
        return  instance;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
        this.image.notifyObserversPathChanged();
        if (this.perspectives != null)
            image.setPerspective(this.perspectives);
    }

    public List<Perspective> getPerspectives() {
        return perspectives;
    }

    public void setPerspectives(List<Perspective> perspectives) {
        if(this.image != null) {
            this.image.setPerspective(perspectives);
        }
        this.perspectives = perspectives;
        this.perspectives.forEach(Perspective::notifyObserverObjectChanged);
    }
    public void addPerspective(Perspective perspective)
    {
        perspective.setId(perspectives.size());
        this.perspectives.add(perspective);
    }

    public void resetPerspective()
    {
        this.image.perspectives = new ArrayList<>();
        this.perspectives = new ArrayList<>();
    }
}
