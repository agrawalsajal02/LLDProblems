package pubsubsystem.phase1;

public interface Subscriber {
    String getId();

    void consume(String topicName, Message message, int offset);
}
