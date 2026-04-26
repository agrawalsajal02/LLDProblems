package companiesProblem.uber.lld.meetingscheuler;

import java.util.Objects;

public final class User {
    private final String userId;
    private final String name;
    private final String email;

    public User(String userId, String name, String email) {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("User id is required.");
        }
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof User)) {
            return false;
        }
        User that = (User) other;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "User{"
            + "userId='" + userId + '\''
            + ", name='" + name + '\''
            + '}';
    }
}
