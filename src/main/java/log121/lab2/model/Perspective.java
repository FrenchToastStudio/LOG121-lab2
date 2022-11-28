package log121.lab2.model;

public class Perspective extends Subject{
    private Position position;
    private int zoom;

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

    public void setPosition(Position position) {
        this.position = position;
        notifyAllObservers();
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
        notifyAllObservers();
    }

    private void notifyAllObservers(){
        notifyObservers(this.getX(),this.getY(),this.getZoom());
    }
}
