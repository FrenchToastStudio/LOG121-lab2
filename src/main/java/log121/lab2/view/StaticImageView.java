package log121.lab2.view;

import log121.lab2.controller.Command;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

public class StaticImageView extends ImageView {

    private List<Command> commands;
    private int xPosition, yPosition, zoom;

    public StaticImageView() {
        super(new ArrayList<Command>() {});

        this.xPosition = this.getWidth()/2;
        this.yPosition = this.getHeight()/2;
        this.zoom = -2;

        activate();
    }

    public StaticImageView(Color bg)
    {
        this();
        this.setBackground(bg);
    }

    public void showImage(String imagePath) {
        super.showImage(imagePath, xPosition/2, yPosition/2, zoom);
    }

    public StaticImageView(List<Command> commands) {
        super(commands);
    }

    @Override
    public void update() {

    }

    @Override
    public void updatePath(String string) {
        this.showImage(string);
    }
}
