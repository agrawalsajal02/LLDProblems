package interviewpatterns.behavioral.mediator;

public final class ChatUser {
    private final String name;
    private final ChatMediator mediator;

    public ChatUser(String name, ChatMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public String getName() {
        return name;
    }

    public void send(String message) {
        mediator.sendMessage(name, message);
    }

    public void receive(String fromUser, String message) {
        System.out.println(name + " received from " + fromUser + ": " + message);
    }
}
