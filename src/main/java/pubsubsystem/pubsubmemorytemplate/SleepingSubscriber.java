package pubsubsystem.pubsubmemorytemplate;

public final class SleepingSubscriber implements ISubscriber {
    private final String id;
    private final long sleepTimeInMillis;

    public SleepingSubscriber(String id, long sleepTimeInMillis) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("subscriber id cannot be blank");
        }
        this.id = id;
        this.sleepTimeInMillis = sleepTimeInMillis;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void consume(Message message) throws InterruptedException {
        System.out.println("Subscriber " + id + " started consuming " + message.getMsg());
        Thread.sleep(sleepTimeInMillis);
        System.out.println("Subscriber " + id + " done consuming " + message.getMsg());
    }
}
