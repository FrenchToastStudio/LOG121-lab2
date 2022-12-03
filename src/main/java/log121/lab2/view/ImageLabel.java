package log121.lab2.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

public class ImageLabel extends JLabel {

    private int x, y, zoom;
    private static final double zoomScale = 5;

    private BufferedImage image;

    public ImageLabel()
    {
        this.setBackground(Color.black);

    }
    public ImageLabel(BufferedImage image, int x, int y, int zoom)
    {
        setPerspective(x,y, getWidth(), getHeight());
        setImageIcon(image);
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

        this.zoom = zoom;

        Image resizedImage = image.getScaledInstance(width, height,
                Image.SCALE_SMOOTH);
        setIcon(resizedImage);

        int xOffset = width/2;
        int yOffset = height/2;
        setBounds(this.x - xOffset,this.y - yOffset, width, height);
    }

    public void setImageIcon(BufferedImage image) {
        this.image = image;
        setIcon(new ImageIcon(image));
    }

    public void setIcon(Image image) {
        setIcon(new ImageIcon(image));
    }



}
