package log121.lab2.view;

public interface Observer {

    void update(int x, int y,int zoom);
    void update(String path);
    void update();
}
