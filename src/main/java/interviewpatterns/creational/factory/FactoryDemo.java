package interviewpatterns.creational.factory;

public final class FactoryDemo {
    public static void main(String[] args) {
        Notification notification = NotificationFactory.create("email");
        notification.send("Interview reminder");
    }
}
