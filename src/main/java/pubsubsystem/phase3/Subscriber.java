package pubsubsystem.phase3;

public interface Subscriber {
    String getId();

    void consume(String topicName, Message message, long offset);
}
