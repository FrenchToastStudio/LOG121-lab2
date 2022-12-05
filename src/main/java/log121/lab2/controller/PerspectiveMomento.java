package log121.lab2.controller;

import log121.lab2.model.*;

public class PerspectiveMomento {
    private Position position;
    private int width;
    private int height;
    private int zoom;

    public PerspectiveMomento(Perspective perspective){
        this.position = new Position(perspective.getX(), perspective.getY());
        this.width = perspective.getWidth();
        this.height = perspective.getHeight();
        this.zoom = perspective.getZoom();
    }

    public void setPerspectiveToState(Perspective perspective){
        perspective.setPosition(this.position);
        perspective.setHeight(this.height);
        perspective.setWidth(this.width);
        perspective.setZoom(this.zoom);

    }

}
