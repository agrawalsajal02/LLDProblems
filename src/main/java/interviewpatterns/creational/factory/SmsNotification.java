package interviewpatterns.creational.factory;

public class SmsNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("SMS sent: " + message);
    }
}
