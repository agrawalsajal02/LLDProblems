package pubsubsystem.pubsubmemorytemplate;

public interface ISubscriber {
    String getId();

    void consume(Message message) throws InterruptedException;
}
