package pubsubsystem.phase3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public final class Topic {
    private static final int DEFAULT_RETENTION_LIMIT = 1_000;

    private final String name;
    private final int retentionLimit;
    private final ArrayDeque<StoredMessage> messages;
    private final ReentrantLock lock;
    private final Condition newMessagePublished;
    private long nextOffset;
    private long baseOffset;

    public Topic(String name) {
        this(name, DEFAULT_RETENTION_LIMIT);
    }

    public Topic(String name, int retentionLimit) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("topic name cannot be blank");
        }
        if (retentionLimit <= 0) {
            throw new IllegalArgumentException("retention limit must be positive");
        }
        this.name = name;
        this.retentionLimit = retentionLimit;
        this.messages = new ArrayDeque<>();
        this.lock = new ReentrantLock();
        this.newMessagePublished = lock.newCondition();
    }

    public String getName() {
        return name;
    }

    long publish(Message message) {
        lock.lock();
        try {
            // Intuition: lock ke andar offset assign + append atomic hai, isliye topic order predictable rahta hai.
            long offset = nextOffset++;
            messages.addLast(new StoredMessage(offset, message));
            if (messages.size() > retentionLimit) {
                // Intuition: bounded memory ke liye oldest message hatao, slow subscriber later drop count me dekhega.
                StoredMessage dropped = messages.removeFirst();
                baseOffset = dropped.getOffset() + 1;
            }
            newMessagePublished.signalAll();
            return offset;
        } finally {
            lock.unlock();
        }
    }

    Optional<StoredMessage> getMessageAt(long offset) {
        lock.lock();
        try {
            // Intuition: requested offset retained window ke bahar hai toh message already unavailable hai.
            if (offset < baseOffset || offset >= nextOffset) {
                return Optional.empty();
            }
            for (StoredMessage storedMessage : messages) {
                if (storedMessage.getOffset() == offset) {
                    return Optional.of(storedMessage);
                }
            }
            return Optional.empty();
        } finally {
            lock.unlock();
        }
    }

    long getNextOffset() {
        lock.lock();
        try {
            return nextOffset;
        } finally {
            lock.unlock();
        }
    }

    long getBaseOffset() {
        lock.lock();
        try {
            return baseOffset;
        } finally {
            lock.unlock();
        }
    }

    void awaitMessageAfter(long offset) throws InterruptedException {
        lock.lock();
        try {
            // Intuition: while loop spurious wakeups handle karta hai aur missed signal ka risk kam karta hai.
            while (offset >= nextOffset) {
                newMessagePublished.await();
            }
        } finally {
            lock.unlock();
        }
    }

    void signalWorkers() {
        lock.lock();
        try {
            // Intuition: publish/reset/unsubscribe ke baad workers ko poke karna padta hai.
            newMessagePublished.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public List<Message> snapshotMessages() {
        lock.lock();
        try {
            // Intuition: copy return karte hain taaki caller internal log mutate na kar sake.
            List<Message> snapshot = new ArrayList<>();
            for (StoredMessage storedMessage : messages) {
                snapshot.add(storedMessage.getMessage());
            }
            return snapshot;
        } finally {
            lock.unlock();
        }
    }
}
