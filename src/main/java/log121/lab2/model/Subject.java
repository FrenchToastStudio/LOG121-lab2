package log121.lab2.model;

import log121.lab2.view.Observer;

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

    public void notifyObserversPathChanged(String path){
        for (Observer o : observers){
            o.updatePath(path);
        }
    }

    public void notifyObserversPositionChanged(int x, int y){
        for (Observer o : observers){
            o.updatePosition(x,y);
        }
    }

    public void notifyObserverZoomChanged(int height, int width){
        for (Observer o : observers){
            o.updateZoom(height, width);
        }
    }


}

