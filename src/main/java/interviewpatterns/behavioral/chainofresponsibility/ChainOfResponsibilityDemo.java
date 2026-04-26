package interviewpatterns.behavioral.chainofresponsibility;

public final class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        SupportHandler levelOne = new LevelOneSupport();
        SupportHandler levelTwo = new LevelTwoSupport();
        SupportHandler manager = new ManagerSupport();

        levelOne.setNext(levelTwo).setNext(manager);

        levelOne.handle(new SupportRequest(1, "Password reset"));
        levelOne.handle(new SupportRequest(2, "Payment stuck"));
        levelOne.handle(new SupportRequest(3, "Production outage"));
    }
}
