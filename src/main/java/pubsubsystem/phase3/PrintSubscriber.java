package pubsubsystem.phase3;

public final class PrintSubscriber implements Subscriber {
    private final String id;
    private final long processingDelayMillis;

    public PrintSubscriber(String id) {
        this(id, 0);
    }

    public PrintSubscriber(String id, long processingDelayMillis) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("subscriber id cannot be blank");
        }
        this.id = id;
        this.processingDelayMillis = processingDelayMillis;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void consume(String topicName, Message message, long offset) {
        if (processingDelayMillis > 0) {
            try {
                Thread.sleep(processingDelayMillis);
            } catch (InterruptedException interruptedException) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println(
            "[" + id + "] topic=" + topicName +
                " offset=" + offset +
                " message=" + message.getPayload()
        );
    }
}
