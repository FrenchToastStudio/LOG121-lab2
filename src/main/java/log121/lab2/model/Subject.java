package log121.lab2.model;

import log121.lab2.view.Observer;

import java.util.ArrayList;

public abstract class Subject {
    private ArrayList<Observer> observers;

    public void attach(Observer observer){
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

    public void notifyObservers(int x, int y, int zoom){
        for (Observer o : observers){
            o.update(x,y,zoom);
        }
    }

    public void notifyObservers(String path){
        for (Observer o : observers){
            o.update(path);
        }
    }
}

