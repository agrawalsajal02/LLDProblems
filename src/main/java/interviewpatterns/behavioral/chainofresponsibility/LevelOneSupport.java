package interviewpatterns.behavioral.chainofresponsibility;

public final class LevelOneSupport extends SupportHandler {
    @Override
    protected boolean canHandle(SupportRequest request) {
        return request.getSeverity() <= 1;
    }

    @Override
    protected void process(SupportRequest request) {
        System.out.println("Level 1 handled: " + request.getMessage());
    }
}
