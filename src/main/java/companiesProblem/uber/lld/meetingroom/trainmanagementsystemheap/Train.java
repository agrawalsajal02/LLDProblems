package companiesProblem.uber.lld.meetingroom.trainmanagementsystemheap;

public final class Train {
    private final String trainId;

    public Train(String trainId) {
        if (trainId == null || trainId.isEmpty()) {
            throw new IllegalArgumentException("Train id is required");
        }
        this.trainId = trainId;
    }

    public String getTrainId() {
        return trainId;
    }
}
