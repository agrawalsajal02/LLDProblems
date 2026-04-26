package splitwise.phase2;

import java.util.HashMap;
import java.util.Map;

public class GroupController {
    private final Map<String, Group> groupById = new HashMap<>();

    public Group createGroup(String groupId, String groupName, User createdByUser) {
        Group group = new Group(groupId, groupName, createdByUser);
        groupById.put(groupId, group);
        return group;
    }

    public Group getGroup(String groupId) {
        return groupById.get(groupId);
    }
}
