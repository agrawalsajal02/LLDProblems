package parkinglot.extensibility.multilevel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ParkingLevel {
    private final String levelId;
    private final List<ParkingRow> rows;

    public ParkingLevel(String levelId, List<ParkingRow> rows) {
        this.levelId = levelId;
        this.rows = new ArrayList<>(rows);
    }

    public String getLevelId() {
        return levelId;
    }

    public List<ParkingRow> getRows() {
        return Collections.unmodifiableList(rows);
    }
}
