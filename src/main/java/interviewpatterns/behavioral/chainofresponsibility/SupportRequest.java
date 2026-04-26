package interviewpatterns.behavioral.chainofresponsibility;

public final class SupportRequest {
    private final int severity;
    private final String message;

    public SupportRequest(int severity, String message) {
        this.severity = severity;
        this.message = message;
    }

    public int getSeverity() {
        return severity;
    }

    public String getMessage() {
        return message;
    }
}
