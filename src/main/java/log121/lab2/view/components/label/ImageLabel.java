package log121.lab2.view.components.label;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Custom label that only shows images
 */
public class ImageLabel extends JLabel {

    private int x, y, maxX, maxY, width, height;
    private static final double zoomScale = 5;

    /**
     * Image that will be set as icon
     */
    private BufferedImage image;

    /**
     * Constructeur
     * @param maxX size of the parent
     * @param maxY size of the parent
     */
    public ImageLabel(int maxX, int maxY)
    {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    /**
     * Updates the size of the position of the image
     * @param x position on the x-axis
     * @param y position on the y-axis
     */
    public void setPerspective(int x, int y){
       this.setPerspective(x,y, getWidth(), getHeight());
    }

    /**
     * updates the size of an image
     * @param width new width of the image
     * @param height new height of the image
     */
    public void setPerspectiveScale(int width, int height){
        this.setPerspective(this.x,this.y,width, height);
    }

    /**
     * updates the size and position on an image
     * @param x position on the x-axis
     * @param y position on the y-axis
     * @param width new width of the image
     * @param height new height of the image
     */
    public void setPerspective(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        resizeImage();
    }

    public void setImageIcon(BufferedImage image) {
        this.image = image;

        if((this.x == 0 && this.y == 0) || (this.height == 0 || this.width == 0))
        {
            this.x = maxX/2;
            this.y = maxY/2;
            this.height = image.getHeight();
            this.width = image.getWidth();
        }

        resizeImage();
    }

    public void resizeImage()
    {
        if(this.image != null && this.width > 0 && this.height >0) {
            Image resizedImage = this.image.getScaledInstance(width, height,
                    Image.SCALE_SMOOTH);
            setIcon(resizedImage);

            int xOffset = width / 2;
            int yOffset = height / 2;
            setBounds(this.x - xOffset, this.y - yOffset, width, height);
        }
    }

    /**
     * Set a new Icon to the Label
     * @param image image to show in label
     */
    public void setIcon(Image image) {
        setIcon(new ImageIcon(image));
    }

    /**
     *
     * @param maxX max value of the visible X-axis of the frame
     * @param maxY max Y-axis of the frame
     */
    public void setMax(int maxX, int maxY)
    {
        this.maxX = maxX;
        this.maxY = maxY;
        int centerX = maxX/2;
        int centerY = maxY/2;
        if((x == maxX/2 && y==maxY) || (x == 0 && y == 0))
        {
            this.x = centerX;
            this.y = centerY;
        }
        resizeImage();
    }

    /**
     * return the current position of the image
     * @return the current position of the image
     */
    public Point getPosition()
    {
        return new Point(x,y);
    }
}
