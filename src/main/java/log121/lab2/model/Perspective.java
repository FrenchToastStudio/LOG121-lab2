package log121.lab2.model;

public class Perspective extends Subject {

    private Position position;
    private int zoom;
    private int height, width;

    public Perspective()
    {

    }

    public Position getPosition(){
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

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setPosition(Position position) {
        this.position = position;
        notifyObserversPositionChanged(position.getX(),position.getY());
    }

    public void setZoom(int zoom) {
        if(this.width <= 1 && this.height <= 1)
            return;

        this.zoom = zoom;
        double zoomWithScaling = zoom*5;

        int zoomWithScalingInt = (int) zoomWithScaling;

        int scaledWidth  = width;
        scaledWidth += zoomWithScalingInt;
        if(scaledWidth <= 1)
            width = 1;
        else
            width = scaledWidth;

        int scaledHeight = zoomWithScalingInt;
        scaledHeight += zoomWithScalingInt;

        if(scaledHeight <= 1)
            this.height = 1;
        else
            this.height = scaledHeight;

        notifyObserverZoomChanged(this.height, this.height);
    }
}
