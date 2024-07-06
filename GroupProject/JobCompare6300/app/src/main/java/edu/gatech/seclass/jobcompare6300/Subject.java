package edu.gatech.seclass.jobcompare6300;

public interface Subject {
    
    void registerObserver(Observer o);

    void notifyObservers();

}
