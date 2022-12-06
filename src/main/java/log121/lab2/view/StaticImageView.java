package log121.lab2.view;

import log121.lab2.controller.commands.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the static image view that is visible in the application
 */
public class StaticImageView extends ImageView {

    private List<Command> commands;
    private int posX, posY, zoom;

    /**
     * The main constructor of the class
     */
    public StaticImageView()
    {
        super(new ArrayList<Command>() {});
        this.zoom = -2;
        activate();
    }

    /**
     * this method allows an image to be shown in the view
     * @param imagePath the path of the image
     */
    public void showImage(String imagePath)
    {
        this.posX = this.getWidth();
        this.posY = this.getHeight();
        super.showImage(imagePath, this.getWidth()/2, this.getHeight()/2, zoom);
    }

    /**
     * to be called when updating the view
     */
    @Override
    public void update() {

    }

    /**
     * to be called when updating the position
     * @param x coord x
     * @param y coord y
     */
    @Override
    public void updatePosition(int x, int y) {

    }

    /**
     * to be called when updating the path
     * @param string the path wanted
     */
    @Override
    public void updatePath(String string) {
        super.updatePath(string);
        this.showImage(string);
    }

    /**
     * to be called when a view is resume aka user tabs back in
     */
    @Override
    public void resume() {
        super.resume();
        if(getWidth() != posX || getHeight() != posY)
        {
            showImage(this.imagePath);
        }

    }
}
