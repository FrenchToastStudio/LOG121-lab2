package log121.lab2.view;

public interface Observer {

    void update();
    void updatePosition(int x, int y);
    void updateZoom(int zoom);
    void updatePath(String string);
}
