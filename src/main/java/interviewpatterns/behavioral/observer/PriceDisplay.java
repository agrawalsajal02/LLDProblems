package interviewpatterns.behavioral.observer;

public class PriceDisplay implements Observer {
    @Override
    public void update(String symbol, double price) {
        System.out.println("Display: " + symbol + " = " + price);
    }
}
