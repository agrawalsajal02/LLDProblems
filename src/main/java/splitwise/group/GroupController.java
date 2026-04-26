package splitwise.group;

import splitwise.user.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GroupController {
    private final Map<String, Group> groupById;

    public GroupController() {
        groupById = new HashMap<>();
    }

    public Group createNewGroup(String groupId, String groupName, User createdByUser) {
        Group group = new Group();
        group.setGroupId(groupId);
        group.setGroupName(groupName);
        group.addMember(createdByUser);
        groupById.put(groupId, group);
        return group;
    }

    public Group getGroup(String groupId) {
        return groupById.get(groupId);
    }

    public Collection<Group> getAllGroups() {
        return groupById.values();
    }
}
