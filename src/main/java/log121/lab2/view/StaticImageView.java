package log121.lab2.view;

import log121.lab2.controller.Command;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class StaticImageView extends ImageView {

    private List<Command> commands;
    private int posX, posY, zoom;
    public StaticImageView()
    {
        super(new ArrayList<Command>() {});
        this.zoom = -2;
        activate();
    }

    public void showImage(String imagePath)
    {
        this.posX = this.getWidth();
        this.posY = this.getHeight();
        super.showImage(imagePath, this.getWidth()/2, this.getHeight()/2, zoom);
    }

    @Override
    public void update() {

    }

    @Override
    public void updatePosition(int x, int y) {

    }

    @Override
    public void updatePath(String string) {
        super.updatePath(string);
        this.showImage(string);
    }

    @Override
    public void resume() {
        super.resume();
        if(getWidth() != posX || getHeight() != posY)
        {
            showImage(this.imagePath);
        }

    }
}
