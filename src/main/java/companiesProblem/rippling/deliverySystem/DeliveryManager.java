package companiesProblem.rippling.deliverySystem;

import java.util.*;

public class DeliveryManager {
    List<Delivery> deliveryList=new ArrayList<>();
    double totalCost=0;
    double settledCost=0;
    PriorityQueue<Delivery> activeDelivery=new PriorityQueue<>((a,b)->{
       return Long.compare(a.endTime,b.endTime);
    });
    Set<Driver> last24HourActiveDriver= new HashSet<>();

    public void addDelivery(Delivery delivery) {
        deliveryList.add(delivery);
        activeDelivery.add(delivery);
    }

    public Delivery getDelivery() {
        for(Delivery d:deliveryList){
            if(d.isUnDelivered){
                return d;
            }
        }
        return null;
    }

    public double cost() {
       return totalCost;
    }

    public void assignDriver(Driver driver) {
        Delivery delivery = getDelivery();
        delivery.assignDriver(driver);
        totalCost +=delivery.cost();
        last24HourActiveDriver.add(driver);

    }

    public void markDeliveryDone(Delivery delivery) {
        delivery.driver.isAvailable= true;
        delivery.isUnDelivered= false;
    }

    void payUpToTime(long upToTime){
        for(Delivery dl:deliveryList) {
            if (dl.endTime <= upToTime && !dl.isSettled) {
                dl.isSettled = true;
                settledCost+=dl.cost();
            }
        }
    }

    double getCostToBePaid(){
        return totalCost-settledCost;
    }



    int getMaxActiveDriversInLast24Hours(long currentTime){

    while(!activeDelivery.isEmpty() && activeDelivery.peek().endTime<currentTime){
        // should not remove driver directly as one driver can have multiple deliveries
        Delivery poll = activeDelivery.poll();
        last24HourActiveDriver.remove(poll.driver);
    }
    return last24HourActiveDriver.size();
    }
}
