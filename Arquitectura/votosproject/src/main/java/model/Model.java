package model;

import Interfaces.Observable;
import Interfaces.Observer;

import java.util.ArrayList;

public abstract class Model implements Observable {
    protected ArrayList<Observer> observers;

    public Model(){
        observers= new ArrayList<>();
    }

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void notifyObservers(Object o) {
        for(Observer observer : observers){
            observer.update(o);
        }
    }
}
