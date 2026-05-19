package companiesProblem.uber.lld.meetingroomSample;

import meetingroomSample.AuditLogEntry;
import meetingroomSample.AuditLogger;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class InMemoryAuditLogger implements AuditLogger {
    private final Map<String, List<meetingroomSample.AuditLogEntry>> logsByRoom = new ConcurrentHashMap<>();

    @Override
    public void log(String roomId, String message) {
        logsByRoom
            .computeIfAbsent(roomId, ignored -> Collections.synchronizedList(new ArrayList<>()))
            .add(new meetingroomSample.AuditLogEntry(roomId, message, Instant.now()));
    }

    @Override
    public List<meetingroomSample.AuditLogEntry> getLogsForRoom(String roomId) {
        return new ArrayList<>(logsByRoom.getOrDefault(roomId, Collections.emptyList()));
    }

    @Override
    public void purgeOlderThan(Duration retention) {
        Instant cutoff = Instant.now().minus(retention);
        for (List<meetingroomSample.AuditLogEntry> roomLogs : logsByRoom.values()) {
            synchronized (roomLogs) {
                Iterator<AuditLogEntry> iterator = roomLogs.iterator();
                while (iterator.hasNext()) {
                    if (iterator.next().getCreatedAt().isBefore(cutoff)) {
                        iterator.remove();
                    }
                }
            }
        }
    }
}
