package designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public class Stock implements Subject {
    private final List<Observer> observers = new ArrayList<>();
    private final String symbol;
    private double price;

    public Stock(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void setPrice(double price) {
        this.price = price;
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(symbol, price);
        }
    }
}
