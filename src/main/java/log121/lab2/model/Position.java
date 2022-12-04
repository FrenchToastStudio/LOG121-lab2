package log121.lab2.model;

import javafx.geometry.Pos;

public class Position {
    private int x;
    private int y;

    public Position setPosition(int x, int y){
        this.x = x;
        this.y = y;
        return this;
    }

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
