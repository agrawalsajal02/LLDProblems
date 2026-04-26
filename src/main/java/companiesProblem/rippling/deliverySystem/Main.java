package companiesProblem.rippling.deliverySystem;//package rippling.deliverySystem;
//
//import java.time.Duration;
//import java.time.Instant;
//
//public class Main {
//    public static void main(String[] args) {
//
//        DriverManager driverManager=new DriverManager();
//        driverManager.addDriver(new Driver("Ram",200));
//
//
//        long epoch = System.currentTimeMillis();
//
//        DeliveryManager deliveryManager=new DeliveryManager();
//        Delivery delivery = new Delivery(epoch, epoch + 3600000);
//        deliveryManager.addDelivery(delivery);
//
//        deliveryManager.assignDriver(driverManager.getDriver());
//
//        System.out.println(deliveryManager.cost());
//
//
//        deliveryManager.markDeliveryDone(delivery);
//
//
//
//
//        Instant instant = Instant.now();
//        Instant plus = instant.plus(Duration.ofHours(4)).sec;a
//
//    }
//}
