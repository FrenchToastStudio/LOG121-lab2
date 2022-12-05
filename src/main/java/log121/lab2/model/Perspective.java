package log121.lab2.model;

import log121.lab2.view.Observer;

public class Perspective extends Subject {

    private Position position;
    private int zoom;
    private int height, width;
    private final int minSize = 10;
    private final int scaleZoomBoost = 5;

    public Perspective()
    {
        super();
    }

    public Position getPosition(){
        if (this.position == null){
            return new Position(0,0);
        }
        return this.position;

    }

    public int getX(){
        return this.getPosition().getX();
    }

    public int getY(){
        return this.getPosition().getY();
    }

    public int getZoom(){
        return this.zoom;
    }

    public int getHeight() {
        return height;
    }

    public void setSize(int width, int height)
    {
        this.width = width;
        this.height =height;
        notifyObserverSizeChanged(this.height, this.width);
    }

    public Perspective setHeight(int height) {
        this.height = height;
        return this;
    }

    public int getWidth() {
        return width;
    }

    public Perspective setWidth(int width) {
        this.width = width;
        return this;
    }

    public Perspective setPosition(Position position) {
        this.position = position;
        notifyObserversPositionChanged(position.getX(),position.getY());
        return this;
    }
    public Perspective setPosition(int x, int y) {
        return setPosition(new Position(x, y));
    }

    public void setZoom(int zoom) {

        if(this.width <= minSize && this.height <= minSize && zoom <0)
            return;

        this.zoom = zoom;
        double zoomWithScaling = zoom*scaleZoomBoost;

        int zoomWithScalingInt = (int) zoomWithScaling;

        int scaledWidth  = width;
        scaledWidth += zoomWithScalingInt;

        if(scaledWidth <= minSize)
            width = minSize;
        else
            width = scaledWidth;

        int scaledHeight = width - zoomWithScalingInt;
        scaledHeight +=  zoomWithScalingInt;

        if(scaledHeight <= minSize)
            this.height = minSize;
        else
            this.height = scaledHeight;

        notifyObserverSizeChanged(this.height, this.width);
    }

    @Override
    public void attach(Observer observer) {
        super.attach(observer);
        notifyObserverObjectChanged();
    }

    public void notifyObserverObjectChanged()
    {
        notifyObserverSizeChanged(getHeight(), getWidth());
        notifyObserversPositionChanged(getX(), getY());
    }
}
