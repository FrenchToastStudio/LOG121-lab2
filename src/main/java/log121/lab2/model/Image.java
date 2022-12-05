package log121.lab2.model;

import log121.lab2.service.imageStrategy.GifViewStrategy;
import log121.lab2.service.imageStrategy.IImageViewStrategy;
import log121.lab2.service.imageStrategy.PNGViewStrategy;
import log121.lab2.view.Observer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Image extends Subject{

    public List<Perspective> perspectives;
    private IImageViewStrategy imageViewStrategy;
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

        if(path.contains(".gif"))
        {
            this.imageViewStrategy = new GifViewStrategy();
        }
        else
        {
            this.imageViewStrategy = new PNGViewStrategy();
        }

        this.imageViewStrategy.setImage(path);

        this.notifyObserversPathChanged();

        if(this.path != null && this.perspectives != null) {
            perspectives.forEach(perspective ->
            {
                perspective.setSize(this.imageViewStrategy.getWidth(), this.imageViewStrategy.getHeight());
            });
        }
    }

    public void setPerspective(List<Perspective> perspectives)
    {
        this.perspectives = perspectives;
        for (Perspective perspective: this.perspectives) {
            if(this.imageViewStrategy != null && (perspective.getWidth() == 0 || perspective.getHeight() ==0))
            {
                perspective.setHeight(this.imageViewStrategy.getHeight());
                perspective.setWidth(this.imageViewStrategy.getWidth());
            }
        }
    }

    public void addPerspective(Perspective perspective)
    {
        if(this.perspectives == null)
            this.perspectives = new ArrayList<>();
        if(this.imageViewStrategy != null & (perspective.getWidth() ==0 || perspective.getHeight() ==0))
        {
            perspective.setHeight(this.imageViewStrategy.getHeight());
            perspective.setWidth(this.imageViewStrategy.getWidth());
        }
        this.perspectives.add(perspective);
    }

    public void notifyObserversPathChanged(){
        if(this.imageViewStrategy == null)
            return;
        this.notifyObserversPathChanged(this.imageViewStrategy);
    }

    @Override
    public void attach(Observer observer) {
        super.attach(observer);
        if(this.imageViewStrategy != null)
        {
            notifyObserversPathChanged();
        }
    }
}
