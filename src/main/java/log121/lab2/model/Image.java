package log121.lab2.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Image extends Subject{

    public List<Perspective> perspectives;
    private BufferedImage bufferedImage;
    public Image()
    {

    }

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        try {
            bufferedImage = ImageIO.read(new File(this.path));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.notifyObserversPathChanged(this.path);

        if(this.path != null) {
            perspectives.forEach(perspective ->
            {
                perspective.setHeight(bufferedImage.getHeight());
                perspective.setWidth(bufferedImage.getWidth());
            });
        }
    }

    public void addPerspective(List<Perspective> perspective)
    {
        if(this.perspectives == null)
            this.perspectives = perspective;
        this.perspectives.addAll(perspective);
    }

    public void addPerspective(Perspective perspective)
    {
        if(this.perspectives == null)
            this.perspectives = new ArrayList<>();
        this.perspectives.add(perspective);
    }
}
