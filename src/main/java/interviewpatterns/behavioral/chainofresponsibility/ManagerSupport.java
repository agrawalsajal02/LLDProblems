package interviewpatterns.behavioral.chainofresponsibility;

public final class ManagerSupport extends SupportHandler {
    @Override
    protected boolean canHandle(SupportRequest request) {
        return request.getSeverity() >= 3;
    }

    @Override
    protected void process(SupportRequest request) {
        System.out.println("Manager handled: " + request.getMessage());
    }
}
