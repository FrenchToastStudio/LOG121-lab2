package log121.lab2.view;

import java.awt.image.BufferedImage;

/**
 * This class represents the interface Observer
 */
public interface Observer {
    void update();
    /**
     * to be called when updating the position
     * @param x coord x
     * @param y coord y
     */
    void updatePosition(int x, int y);

    /**
     * to be called when using the zoom
     * @param heigth new heigth for the image
     * @param width new width for the image
     */
    void updateZoom(int heigth, int width);

    /**
     * to be called when updating the image
     * @param image to be updated with
     */
    void updateImage(BufferedImage image);
}
