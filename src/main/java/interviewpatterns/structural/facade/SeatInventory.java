package interviewpatterns.structural.facade;

public class SeatInventory {
    public boolean isSeatAvailable(String showId, String seatId) {
        return showId != null && seatId != null;
    }

    public void reserve(String showId, String seatId) {
        System.out.println("Reserved seat " + seatId + " for show " + showId);
    }
}
