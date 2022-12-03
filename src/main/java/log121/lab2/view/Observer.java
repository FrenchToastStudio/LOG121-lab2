package log121.lab2.view;

public interface Observer {

    void update();
    void updatePosition(int x, int y);
    void updateZoom(int heigth, int width);
    void updatePath(String string);
}
