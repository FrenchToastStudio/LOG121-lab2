package log121.lab2.model;

import log121.lab2.service.imageStrategy.IImageViewStrategy;
import log121.lab2.view.Observer;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Subject {
    private ArrayList<Observer> observers;

    public Subject()
    {
        this.observers = new ArrayList<>();
    }

    public void attach(Observer observer){
        if(observers == null)
            this.observers = new ArrayList<Observer>();
        this.observers.add(observer);
    }

    public void detach(Observer observer){
        this.observers.remove(observer);
    }

    public void notifyObservers(){
        for (Observer o : observers){
            o.update();
        }
    }

    public void notifyObserversPathChanged(IImageViewStrategy bufferedImage){
        for (Observer o : observers){
            o.updateImage(bufferedImage);
        }
    }

    public void notifyObserversPositionChanged(int x, int y){
        for (Observer o : observers){
            o.updatePosition(x,y);
        }
    }

    public void notifyObserverSizeChanged(int height, int width){
        for (Observer o : observers){
            o.updateZoom(height, width);
        }
    }


}

