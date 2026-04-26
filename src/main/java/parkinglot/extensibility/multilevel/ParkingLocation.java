package parkinglot.extensibility.multilevel;

public final class ParkingLocation {
    private final String levelId;
    private final String rowId;
    private final String spotId;

    public ParkingLocation(String levelId, String rowId, String spotId) {
        this.levelId = levelId;
        this.rowId = rowId;
        this.spotId = spotId;
    }

    public String getLevelId() {
        return levelId;
    }

    public String getRowId() {
        return rowId;
    }

    public String getSpotId() {
        return spotId;
    }

    @Override
    public String toString() {
        return "ParkingLocation{"
            + "levelId='" + levelId + '\''
            + ", rowId='" + rowId + '\''
            + ", spotId='" + spotId + '\''
            + '}';
    }
}
