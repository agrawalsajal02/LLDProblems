package interviewpatterns.behavioral.chainofresponsibility;

public final class LevelTwoSupport extends SupportHandler {
    @Override
    protected boolean canHandle(SupportRequest request) {
        return request.getSeverity() == 2;
    }

    @Override
    protected void process(SupportRequest request) {
        System.out.println("Level 2 handled: " + request.getMessage());
    }
}
