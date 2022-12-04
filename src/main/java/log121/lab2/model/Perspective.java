package log121.lab2.model;

public class Perspective extends Subject {

    private Position position;
    private int zoom;
    private int height, width;
    private final int minSize = 10;
    private final int scaleZoomBoost = 5;

    public Perspective()
    {

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

        notifyObserverZoomChanged(this.height, this.width);
    }
}
