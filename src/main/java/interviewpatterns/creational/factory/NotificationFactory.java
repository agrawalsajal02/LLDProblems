package interviewpatterns.creational.factory;

public final class NotificationFactory {
    private NotificationFactory() {
    }

    public static Notification create(String type) {
        if ("email".equalsIgnoreCase(type)) {
            return new EmailNotification();
        }
        if ("sms".equalsIgnoreCase(type)) {
            return new SmsNotification();
        }
        throw new IllegalArgumentException("Unknown notification type: " + type);
    }
}
