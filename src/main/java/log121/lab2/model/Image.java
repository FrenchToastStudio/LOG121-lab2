package log121.lab2.model;

import java.util.ArrayList;

public class Image {
    private String path;
    private ArrayList<Perspective> perspectiveList;

    public String getPath() {
        return path;
    }

    public void addPerspectiveList(Perspective perspective) {
        perspectiveList.add(perspective);
    }
}
