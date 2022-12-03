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

    public static Store getInstance()
    {
        if(instance == null) {
            instance = new Store();
            instance.setPerspectives(new ArrayList<>());
        }
        return  instance;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
        if(image.getPath() != null) {
            perspectives.forEach(perspective ->
            {
                try {
                    BufferedImage imageFile = ImageIO.read(new File(this.image.getPath()));

                    perspective.setHeight(imageFile.getHeight());
                    perspective.setWidth(imageFile.getWidth());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public List<Perspective> getPerspectives() {
        return perspectives;
    }

    public void setPerspectives(List<Perspective> perspectives) {
        this.perspectives = perspectives;
    }
    public void addPerspective(Perspective perspective)
    {
        this.perspectives.add(perspective);
    }
}
