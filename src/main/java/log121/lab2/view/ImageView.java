package log121.lab2.view;

import log121.lab2.controller.Command;
import log121.lab2.controller.CommandManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class ImageView extends JPanel implements View, Observer{

    private List<Command> commands;
    private ImageLabel imageLabel;
    protected String imagePath;

    public ImageView(List<Command> commands) {
        this.commands = commands;
        imageLabel = new ImageLabel();
        setLayout(null);
        add(imageLabel);
    }
    /**
     * Show an image from a path, Have perspective to 1 by defauly
     * @param imagePath
     * @param x
     * @param y
     */
    public void showImage(String imagePath, int x, int y)
    {
        showImage(imagePath, x, y, 1);
    }
    /**
     * Show an image from a path, Have perspective to 1 by defauly
     * @param imagePath
     * @param x
     * @param y
     * @param perspective
     */
    public void showImage(String imagePath, int x, int y, int perspective)
    {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            showImage(image, x, y, perspective);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showImage(BufferedImage image, int x, int y, int zoom)
    {
        imageLabel.setImageIcon(image);
        imageLabel.setPerspective(x, y, zoom);
    }
    public void updatePath(String string)
    {
        this.imagePath = string;
    }

    public void activate()
    {
        CommandManager.getInstance().attachCommand(this, commands);
    }

    public void pause()
    {
        CommandManager.getInstance().detachCommand(this);
    }

    public void resume()
    {
        CommandManager.getInstance().attachCommand(this, commands);
    }

    public void destroy()
    {
        CommandManager.getInstance().detachCommand(this);
    }

}
