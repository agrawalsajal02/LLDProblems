package pubsubsystem.phase1;

public final class PrintSubscriber implements Subscriber {
    private final String id;

    public PrintSubscriber(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("subscriber id cannot be blank");
        }
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void consume(String topicName, Message message, int offset) {
        System.out.println("[" + id + "] " + topicName + "@" + offset + " -> " + message.getPayload());
    }
}
