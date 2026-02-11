package designpattern.factory;

public final class FactoryMain {
    public static void main(String[] args) {
        Notification notification = NotificationFactory.create("email");
        notification.send("Hello");
    }
}
