package log121.lab2.model;

import log121.lab2.view.Observer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Image extends Subject{

    public List<Perspective> perspectives;
    private BufferedImage bufferedImage;
    private String path;
    public Image()
    {
        super();
    }


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

        this.notifyObserversPathChanged();

        if(this.path != null && this.perspectives != null) {
            perspectives.forEach(perspective ->
            {
                perspective.setSize(bufferedImage.getWidth(), bufferedImage.getHeight());
            });
        }
    }

    public void setPerspective(List<Perspective> perspectives)
    {
        this.perspectives = perspectives;
        for (Perspective perspective: this.perspectives) {
            if(this.bufferedImage != null && (perspective.getWidth() == 0 || perspective.getHeight() ==0))
            {
                perspective.setHeight(this.bufferedImage.getHeight());
                perspective.setWidth(this.bufferedImage.getWidth());
            }
        }
    }

    public void addPerspective(Perspective perspective)
    {
        if(this.perspectives == null)
            this.perspectives = new ArrayList<>();
        if(this.bufferedImage != null & (perspective.getWidth() ==0 || perspective.getHeight() ==0))
        {
            perspective.setHeight(this.bufferedImage.getHeight());
            perspective.setWidth(this.bufferedImage.getWidth());
        }
        this.perspectives.add(perspective);
    }

    public void notifyObserversPathChanged(){
        if(this.bufferedImage == null)
            return;
        this.notifyObserversPathChanged(this.bufferedImage);
    }

    @Override
    public void attach(Observer observer) {
        super.attach(observer);
        if(this.bufferedImage != null)
        {
            notifyObserversPathChanged();
        }
    }
}
