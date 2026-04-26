package splitwise.user;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserController {
    private final Map<String, User> userById;

    public UserController() {
        userById = new HashMap<>();
    }

    public void addUser(User user) {
        userById.put(user.getUserId(), user);
    }

    public User getUser(String userId) {
        return userById.get(userId);
    }

    public Collection<User> getAllUsers() {
        return userById.values();
    }
}
