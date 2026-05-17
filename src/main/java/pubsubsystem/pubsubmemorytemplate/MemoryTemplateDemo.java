package pubsubsystem.pubsubmemorytemplate;

public final class MemoryTemplateDemo {
    private MemoryTemplateDemo() {
    }

    public static void main(String[] args) throws InterruptedException {
        Queue queue = new Queue();
        Topic orders = queue.createTopic("orders");

        ISubscriber orderService = new SleepingSubscriber("order-service", 300);
        ISubscriber dashboard = new SleepingSubscriber("dashboard", 100);

        queue.subscribe(orders, orderService);
        queue.subscribe(orders, dashboard);

        queue.sendMessage(new Message("order-1-created"), orders);
        queue.sendMessage(new Message("order-2-created"), orders);

        Thread.sleep(1_000);

        queue.resetOffset(orders, dashboard, 0);
        Thread.sleep(1_000);
    }
}
