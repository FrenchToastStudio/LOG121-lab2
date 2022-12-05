package log121.lab2.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageLabel extends JLabel {

    private int x, y, maxX, maxY, width, height;
    private static final double zoomScale = 5;

    private BufferedImage image;

    public ImageLabel(int maxX, int maxY)
    {
        this.maxX = maxX;
        this.maxY = maxY;
    }
    public void setPerspective(int x, int y){
       this.setPerspective(x,y, getWidth(), getHeight());
    }

    public void setPerspectiveScale(int width, int height){
        this.setPerspective(this.x,this.y,width, height);
    }

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

    public void setIcon(Image image) {
        setIcon(new ImageIcon(image));
    }

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

}
