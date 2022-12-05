package log121.lab2.controller;

import log121.lab2.model.*;
import log121.lab2.view.ImageView;

public class ImageController {
    private Image image;
    ImageView view;

    public ImageController(ImageView imageView)
    {
        setView(imageView);
    }

    public ImageController()
    {

    }

    public void setView(ImageView imageView)
    {
        this.view = imageView;
    }

}
