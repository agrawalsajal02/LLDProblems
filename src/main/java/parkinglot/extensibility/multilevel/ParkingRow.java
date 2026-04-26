package parkinglot.extensibility.multilevel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ParkingRow {
    private final String rowId;
    private final List<ParkingSpot> spots;

    public ParkingRow(String rowId, List<ParkingSpot> spots) {
        this.rowId = rowId;
        this.spots = new ArrayList<>(spots);
    }

    public String getRowId() {
        return rowId;
    }

    public List<ParkingSpot> getSpots() {
        return Collections.unmodifiableList(spots);
    }
}
