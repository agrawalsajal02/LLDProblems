package interviewpatterns.behavioral.chainofresponsibility;

public abstract class SupportHandler {
    private SupportHandler next;

    public SupportHandler setNext(SupportHandler next) {
        this.next = next;
        return next;
    }

    public final void handle(SupportRequest request) {
        if (canHandle(request)) {
            process(request);
            return;
        }

        if (next != null) {
            next.handle(request);
            return;
        }

        System.out.println("No handler available for request: " + request.getMessage());
    }

    protected abstract boolean canHandle(SupportRequest request);

    protected abstract void process(SupportRequest request);
}
