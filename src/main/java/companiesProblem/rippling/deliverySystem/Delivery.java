package companiesProblem.rippling.deliverySystem;

public class Delivery {
    public boolean isUnDelivered;
    public boolean isSettled;
    Driver driver;
    long startTime;
    long endTime;

    public Delivery(long startTime, long endTime) {
        this.startTime=startTime;
        this.endTime=endTime;
        isUnDelivered= false;
    }


    public Double cost() {
        long st=endTime-startTime;
        double l = (double)((st) / 3600000);
        return l* driver.hourlyRate;
    }

    public void assignDriver(Driver driver) {
        driver.isAvailable= false;
        this.driver=driver;
    }
}
