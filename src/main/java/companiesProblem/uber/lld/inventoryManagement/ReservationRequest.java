package companiesProblem.uber.lld.inventoryManagement;

public final class ReservationRequest {
    private final String userId;
    private final CarSearchCriteria searchCriteria;
    private final TimeSlot requestedSlot;

    public ReservationRequest(String userId, CarSearchCriteria searchCriteria, TimeSlot requestedSlot) {
        this.userId = userId;
        this.searchCriteria = searchCriteria;
        this.requestedSlot = requestedSlot;
    }

    public String getUserId() {
        return userId;
    }

    public CarSearchCriteria getSearchCriteria() {
        return searchCriteria;
    }

    public TimeSlot getRequestedSlot() {
        return requestedSlot;
    }
}
