package pubsubsystem.phase2;

public final class PrintSubscriber implements Subscriber {
    private final String id;
    private final long delayMillis;

    public PrintSubscriber(String id, long delayMillis) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("subscriber id cannot be blank");
        }
        this.id = id;
        this.delayMillis = delayMillis;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void consume(String topicName, Message message, long offset) {
        if (delayMillis > 0) {
            try {
                Thread.sleep(delayMillis);
            } catch (InterruptedException interruptedException) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println("[" + id + "] " + topicName + "@" + offset + " -> " + message.getPayload());
    }
}
