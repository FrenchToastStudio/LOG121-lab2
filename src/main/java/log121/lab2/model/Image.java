package log121.lab2.model;

public class Image extends Subject{
    public Image()
    {

    }
    public Image(String path) {
        this.path = path;
    }

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        this.notifyObserversPathChanged(this.path);
    }
}
