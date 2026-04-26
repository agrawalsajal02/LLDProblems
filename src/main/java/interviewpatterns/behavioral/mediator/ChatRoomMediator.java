package interviewpatterns.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

public final class ChatRoomMediator implements ChatMediator {
    private final List<ChatUser> users = new ArrayList<>();

    @Override
    public void addUser(ChatUser user) {
        users.add(user);
    }

    @Override
    public void sendMessage(String fromUser, String message) {
        for (ChatUser user : users) {
            if (!user.getName().equals(fromUser)) {
                user.receive(fromUser, message);
            }
        }
    }
}
