package companiesProblem.uber.lld.meetingroom.trainmanagementsystemheap;

public final class Platform {
    private final String platformId;

    public Platform(String platformId) {
        if (platformId == null || platformId.isEmpty()) {
            throw new IllegalArgumentException("Platform id is required");
        }
        this.platformId = platformId;
    }

    public String getPlatformId() {
        return platformId;
    }

    @Override
    public String toString() {
        return platformId;
    }
}
