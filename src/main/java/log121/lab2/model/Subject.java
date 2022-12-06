package log121.lab2.model;

import log121.lab2.view.Observer;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *  Subject represents the abstract class
 */
public abstract class Subject {
    private ArrayList<Observer> observers;

    /**
     * default constructor that initialises the observer ArrayList
     */
    public Subject()
    {
        this.observers = new ArrayList<>();
    }

    /**
     * This method allows to attach an observer
     * @param observer to be attached
     */
    public void attach(Observer observer){
        if(observers == null)
            this.observers = new ArrayList<Observer>();
        this.observers.add(observer);
    }

    /**
     * This method allows to detach an observer
     * @param observer the observer to detach
     */
    public void detach(Observer observer){
        this.observers.remove(observer);
    }

    /**
     * this method allows to notify all the observers in our list to run the update method
     */
    public void notifyObservers(){
        for (Observer o : observers){
            o.update();
        }
    }

    /**
     * this method allows to notify all the observers in our list to update their image with the specified one
     * @param bufferedImage the specified image
     */
    public void notifyObserversPathChanged(BufferedImage bufferedImage){
        for (Observer o : observers){
            o.updateImage(bufferedImage);
        }
    }

    /**
     * this method allows to notify all the observers in our list to update their position with the specified one
     * @param x the coord x
     * @param y the coord y
     */
    public void notifyObserversPositionChanged(int x, int y){
        for (Observer o : observers){
            o.updatePosition(x,y);
        }
    }

    /**
     * this method allows to notify all the observers in our list to update their zoom/size with the specified one
     * @param height
     * @param width
     */
    public void notifyObserverSizeChanged(int height, int width){
        for (Observer o : observers){
            o.updateZoom(height, width);
        }
    }


}

