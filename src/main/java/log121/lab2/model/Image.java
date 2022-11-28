package log121.lab2.model;

public class Image extends Subject {
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        notifyObservers(path);
    }

}
