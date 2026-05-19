package companiesProblem.uber.lld.meetingroomSample;

import meetingroomSample.AuditLogEntry;

import java.time.Duration;
import java.util.List;

public interface AuditLogger {
    void log(String roomId, String message);

    List<AuditLogEntry> getLogsForRoom(String roomId);

    void purgeOlderThan(Duration retention);
}
