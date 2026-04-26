package inventorymanagement.extensibility;

public class StockReservation {
    private final String reservationId;
    private final String productId;
    private final int quantity;
    private final long expiresAtMs;

    public StockReservation(String reservationId, String productId, int quantity, long expiresAtMs) {
        this.reservationId = reservationId;
        this.productId = productId;
        this.quantity = quantity;
        this.expiresAtMs = expiresAtMs;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getExpiresAtMs() {
        return expiresAtMs;
    }
}
