package log121.lab2.model;

import log121.lab2.view.Observer;

public class Perspective extends Subject {

    private int id;
    private Position position;
    private int zoom;
    private int height, width;
    private final int minSize = 10;
    private final int scaleZoomBoost = 5;

    /**
     * Constructor that represent the perspective of an image that can be modified in this app
     */
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

    /**
     * changes the width of the image to zoom on it
     * @param zoom zoom value  -1 to unZoom +1 to zoom in
     */
    public void setZoom(int zoom) {

        if(zoom != 0) {
            double scaledWidth = getWidth();
            double scaledHeight = getHeight();

            this.zoom = zoom;
            double zoomWithScaling = zoom * scaleZoomBoost;
            double zoomPercentage = (1+zoomWithScaling/100);

            scaledWidth = scaledWidth * zoomPercentage;
            scaledHeight = scaledHeight * zoomPercentage;
            if (!(zoom < 0 && (scaledWidth <= minSize || scaledWidth <= minSize))) {
                this.width = (int) Math.ceil(scaledWidth);
                this.height = (int) Math.ceil(scaledHeight);
            }
            setSize(width, height);
        }
    }

    /**
     * attach observ that will be notify when changed are made on this class
     * @param observer obervers to attach to this subject
     */
    @Override
    public void attach(Observer observer) {
        super.attach(observer);
        notifyObserverObjectChanged();
    }

    /**
     * notify observers that the ENTIRE object has changed
     */
    public void notifyObserverObjectChanged()
    {
        notifyObserverSizeChanged(getHeight(), getWidth());
        notifyObserversPositionChanged(getX(), getY());
    }

    /**
     * sets a id to th perspective (mostly used for debugging)
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
}
