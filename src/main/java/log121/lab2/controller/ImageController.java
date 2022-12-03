package log121.lab2.controller;

import log121.lab2.model.*;
import log121.lab2.view.ImageView;

public class ImageController {
    private Image image;

    public ImageController(ImageView imageView)
    {
        Store.getInstance().getImage().attach(imageView);
    }

    public void loadImage(){
    }

}
