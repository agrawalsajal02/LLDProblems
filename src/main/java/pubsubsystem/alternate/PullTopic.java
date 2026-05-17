package pubsubsystem.alternate;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

final class PullTopic {
    private final String name;
    private final int retentionLimit;
    private final ArrayDeque<PublishedMessage> messages;
    private long baseOffset;
    private long nextOffset;

    PullTopic(String name, int retentionLimit) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("topic name cannot be blank");
        }
        if (retentionLimit <= 0) {
            throw new IllegalArgumentException("retention limit must be positive");
        }
        this.name = name;
        this.retentionLimit = retentionLimit;
        this.messages = new ArrayDeque<>();
    }

    synchronized long publish(Message message) {
        // Intuition: pull model me bhi topic append-only log hai, bas delivery subscriber poll karega.
        long offset = nextOffset++;
        messages.addLast(new PublishedMessage(offset, message));
        if (messages.size() > retentionLimit) {
            PublishedMessage removed = messages.removeFirst();
            baseOffset = removed.getOffset() + 1;
        }
        return offset;
    }

    synchronized SubscriptionCursor subscribe(String subscriberId) {
        // Intuition: new pull subscriber default se latest offset se start karta hai.
        return new SubscriptionCursor(name, subscriberId, nextOffset);
    }

    synchronized List<PublishedMessage> poll(SubscriptionCursor cursor, int maxMessages) {
        if (!name.equals(cursor.getTopicName())) {
            throw new IllegalArgumentException("cursor belongs to different topic");
        }
        if (maxMessages <= 0) {
            throw new IllegalArgumentException("maxMessages must be positive");
        }

        // Intuition: cursor retention se peeche hai toh us gap ko drop maan kar base offset par jump karo.
        if (cursor.getNextOffset() < baseOffset) {
            cursor.addDropped(baseOffset - cursor.getNextOffset());
            cursor.setNextOffset(baseOffset);
        }

        List<PublishedMessage> result = new ArrayList<>();
        for (PublishedMessage message : messages) {
            if (message.getOffset() < cursor.getNextOffset()) {
                continue;
            }
            if (result.size() == maxMessages) {
                break;
            }
            result.add(message);
        }

        if (!result.isEmpty()) {
            PublishedMessage last = result.get(result.size() - 1);
            cursor.setNextOffset(last.getOffset() + 1);
        }
        return result;
    }
}
