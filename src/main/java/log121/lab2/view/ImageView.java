package log121.lab2.view;

import log121.lab2.controller.commands.Command;
import log121.lab2.controller.commands.CommandManager;
import log121.lab2.controller.ViewState;
import log121.lab2.view.components.label.ImageLabel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the ImageView
 */
public abstract class ImageView extends JPanel implements View, Observer{

    private ViewState viewState;

    private List<Command> commands;
    private ImageLabel imageLabel;
    protected String imagePath;

    protected int maxWidth;
    protected int maxHeight;

    /**
     * Constructor for the class
     * @param commands is the list of commands
     */
    public ImageView(List<Command> commands) {

        if(commands == null)
        {
            commands = new ArrayList<>();
        }
        this.commands = commands;
        imageLabel = new ImageLabel(getWidth(), getHeight());
        setLayout(null);
        add(imageLabel);
    }

    public void setSize(int width, int height)
    {
        imageLabel.setMax(width, height);
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
        if(imagePath == null)
            return;
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            showImage(image, x, y, image.getWidth(), image.getHeight());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showImage(BufferedImage image, int x, int y, int width, int height)
    {
        imageLabel.setImageIcon(image);
        imageLabel.setPerspective(x, y, height, width);
    }

    public void updatePosition(int x,int y){
        imageLabel.setPerspective(x,y);
    }

    @Override
    public void updateZoom(int heigth, int width) {
        imageLabel.setPerspectiveScale(width, heigth);
    }


    public void updatePath(String string)
    {
    }

    @Override
    public void update() {

    }

    @Override
    public void updateImage(BufferedImage image) {
        if(image != null) {
            imageLabel.setImageIcon(image);
        }
    }

    public void activate()
    {
        if(viewState != null) {
            if(!viewState.equals(ViewState.Activated)) {
                viewState = ViewState.Activated;
                CommandManager.getInstance().attachCommand(this, commands);
                setFocusable(true);
                requestFocus();
            }
        }
    }

    public void pause()
    {
        viewState = ViewState.Paused;
        CommandManager.getInstance().detachCommand(this);
        setFocusable(false);
    }

    public void resume()
    {
        if(viewState != null) {
            if (!viewState.equals(ViewState.Activated)) {
                viewState = ViewState.Activated;
                CommandManager.getInstance().attachCommand(this, commands);
                setFocusable(true);
                requestFocus();
            }
        }
    }

    public void destroy()
    {
        CommandManager.getInstance().detachCommand(this);
    }

    public void addCommand(Command command)
    {
        this.commands.add(command);
    }

    public ImageLabel getImageLabel(){
        return imageLabel;
    }

    public void setMaxSize(int width, int height)
    {
        imageLabel.setMax(width, height);
    }

    protected Point getImagePosition()
    {
        return imageLabel.getPosition();
    }
}
