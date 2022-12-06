package log121.lab2.model;


/**
 * Class that represent the position of an object in this app
 */
public class Position {
    private int x;
    private int y;

    /**
     * Setter that is for some reason BEFORE the constructor
     * @param x position on the x-axis
     * @param y position on the y-axis
     * @return this Position so that we can continuer setting things on this position
     */
    public Position setPosition(int x, int y){
        this.x = x;
        this.y = y;
        return this;
    }

    /**
     * Constructor
     * @param x position on the x-axis
     * @param y position on the y-axis
     */
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
