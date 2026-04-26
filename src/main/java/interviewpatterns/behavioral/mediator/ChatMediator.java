package interviewpatterns.behavioral.mediator;

public interface ChatMediator {
    void addUser(ChatUser user);

    void sendMessage(String fromUser, String message);
}
