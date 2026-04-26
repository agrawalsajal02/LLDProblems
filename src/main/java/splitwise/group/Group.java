package splitwise.group;

import splitwise.expense.Expense;
import splitwise.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Group {
    private String groupId;
    private String groupName;
    private final List<User> groupMembers;
    private final List<Expense> expenseList;

    Group() {
        groupMembers = new ArrayList<>();
        expenseList = new ArrayList<>();
    }

    public void addMember(User member) {
        groupMembers.add(member);
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void addExpense(Expense expense) {
        expenseList.add(expense);
    }

    public List<User> getGroupMembers() {
        return Collections.unmodifiableList(groupMembers);
    }

    public List<Expense> getExpenseList() {
        return Collections.unmodifiableList(expenseList);
    }
}
