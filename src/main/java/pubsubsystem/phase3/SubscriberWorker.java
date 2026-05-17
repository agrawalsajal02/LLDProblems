package pubsubsystem.phase3;

import java.util.Optional;

final class SubscriberWorker implements Runnable {
    private final TopicSubscription subscription;

    SubscriberWorker(TopicSubscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void run() {
        Topic topic = subscription.getTopic();

        while (subscription.isActive()) {
            long nextOffset = subscription.getNextOffset();
            long baseOffset = topic.getBaseOffset();

            if (nextOffset < baseOffset) {
                // Intuition: subscriber bahut slow tha, retained log aage badh gaya, toh missed messages drop maan lo.
                subscription.markDropped(baseOffset - nextOffset);
                subscription.advanceTo(baseOffset);
                nextOffset = baseOffset;
            }

            if (nextOffset >= topic.getNextOffset()) {
                // Intuition: abhi koi new message nahi, worker busy-spin ki jagah condition par wait karega.
                waitForMoreMessages(topic);
                continue;
            }

            // Intuition: at-most-once ke liye delivery se pehle offset claim karte hain; failure pe retry nahi.
            long claimedOffset = subscription.claimCurrentOffset();
            Optional<StoredMessage> storedMessage = topic.getMessageAt(claimedOffset);
            if (storedMessage.isEmpty()) {
                subscription.markDropped(1);
                continue;
            }

            try {
                Message message = storedMessage.get().getMessage();
                subscription.getSubscriber().consume(topic.getName(), message, claimedOffset);
                subscription.markDelivered();
            } catch (RuntimeException runtimeException) {
                // Intuition: callback fail hua toh message drop, redelivery nahi kyunki contract at-most-once hai.
                System.err.println(
                    "Dropped message for subscriber=" + subscription.getSubscriber().getId() +
                        " topic=" + topic.getName() +
                        " offset=" + claimedOffset +
                        " reason=" + runtimeException.getMessage()
                );
            }
        }
    }

    private void waitForMoreMessages(Topic topic) {
        try {
            // Intuition: worker apne current offset ke baad message aane tak park hota hai.
            topic.awaitMessageAfter(subscription.getNextOffset());
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
            subscription.deactivate();
        }
    }
}
