package splitwise.phase2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Group {
    private final String groupId;
    private final String groupName;
    private final List<User> members = new ArrayList<>();
    private final List<Expense> expenses = new ArrayList<>();

    public Group(String groupId, String groupName, User createdByUser) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.members.add(createdByUser);
    }

    public String getGroupId() {
        return groupId;
    }

    public void addMember(User member) {
        members.add(member);
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public List<User> getMembers() {
        return Collections.unmodifiableList(members);
    }
}
