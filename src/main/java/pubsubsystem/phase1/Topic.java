package pubsubsystem.phase1;

import java.util.ArrayList;
import java.util.List;

public final class Topic {
    private final String name;
    private final List<Message> messages;
    private final List<Subscriber> subscribers;

    public Topic(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("topic name cannot be blank");
        }
        this.name = name;
        this.messages = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public synchronized void subscribe(Subscriber subscriber) {
        // Intuition: topic ke andar subscriber list maintain karo, publish ke time same list fan-out hogi.
        subscribers.add(subscriber);
    }

    public synchronized int publish(Message message) {
        // Intuition: pehle log me append karo, phir same order me active subscribers ko message bhejo.
        messages.add(message);
        int offset = messages.size() - 1;
        for (Subscriber subscriber : subscribers) {
            subscriber.consume(name, message, offset);
        }
        return offset;
    }

    public synchronized List<Message> snapshotMessages() {
        // Intuition: internal list expose nahi karni, warna bahar se order corrupt ho sakta hai.
        return new ArrayList<>(messages);
    }
}
