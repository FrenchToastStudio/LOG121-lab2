package log121.lab2.view;

import log121.lab2.service.imageStrategy.IImageViewStrategy;

import java.awt.image.BufferedImage;

public interface Observer {

    void update();
    void updatePosition(int x, int y);
    void updateZoom(int heigth, int width);
    void updateImage(IImageViewStrategy image);
}
