package log121.lab2.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

public class ImageLabel extends JLabel {

    private int x, y, zoom;
    private BufferedImage image;

    public ImageLabel()
    {
        this.setBackground(Color.black);

    }
    public ImageLabel(BufferedImage image, int x, int y, int zoom)
    {
        setPerspective(x,y,zoom);
        setImageIcon(image);
    }
    public void setPerspective(int x, int y, int zoom) {
        this.x = x;
        this.y = y;

        this.zoom = zoom;
        int height = image.getHeight();
        int widht = image.getWidth();
        if (zoom > 0)
        {
            widht *= zoom;
            height *= zoom;
        }
        else if (zoom < 0)
        {
            widht /= -zoom;
            height /= -zoom;
        }
        Image resizedImage = image.getScaledInstance(widht, height,
                Image.SCALE_SMOOTH);
        setIcon(resizedImage);

        int xOffset = widht/2;
        int yOffset = height/2;
        setBounds(this.x - xOffset,this.y - yOffset, widht, height);
    }

    public void setImageIcon(BufferedImage image) {
        this.image = image;
        setIcon(new ImageIcon(image));
    }

    public void setIcon(Image image) {
        setIcon(new ImageIcon(image));
    }



}
