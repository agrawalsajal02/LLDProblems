package inventorymanagement.extensibility;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ReservableWarehouse implements InventoryHolder {
    private final String id;
    private final Map<String, Integer> inventory;
    private final Map<String, Integer> reserved;
    private final Map<String, StockReservation> reservations;

    public ReservableWarehouse(String id) {
        this.id = id;
        this.inventory = new HashMap<>();
        this.reserved = new HashMap<>();
        this.reservations = new HashMap<>();
    }

    @Override
    public synchronized void addStock(String productId, int quantity) {
        inventory.put(productId, inventory.getOrDefault(productId, 0) + quantity);
    }

    @Override
    public synchronized boolean removeStock(String productId, int quantity) {
        if (!checkAvailability(productId, quantity)) {
            return false;
        }
        inventory.put(productId, inventory.getOrDefault(productId, 0) - quantity);
        return true;
    }

    @Override
    public synchronized int getStock(String productId) {
        return inventory.getOrDefault(productId, 0);
    }

    @Override
    public synchronized boolean checkAvailability(String productId, int quantity) {
        if (quantity <= 0) {
            return false;
        }
        int total = inventory.getOrDefault(productId, 0);
        int reservedQty = reserved.getOrDefault(productId, 0);
        return (total - reservedQty) >= quantity;
    }

    public synchronized boolean reserveStock(String productId, int quantity, String reservationId, long timeoutMs) {
        if (!checkAvailability(productId, quantity)) {
            return false;
        }
        reserved.put(productId, reserved.getOrDefault(productId, 0) + quantity);
        reservations.put(
            reservationId,
            new StockReservation(reservationId, productId, quantity, System.currentTimeMillis() + timeoutMs)
        );
        return true;
    }

    public synchronized boolean confirmReservation(String reservationId) {
        StockReservation reservation = reservations.get(reservationId);
        if (reservation == null || System.currentTimeMillis() > reservation.getExpiresAtMs()) {
            return false;
        }

        String productId = reservation.getProductId();
        inventory.put(productId, inventory.getOrDefault(productId, 0) - reservation.getQuantity());
        reserved.put(productId, reserved.getOrDefault(productId, 0) - reservation.getQuantity());
        reservations.remove(reservationId);
        return true;
    }

    public synchronized void releaseReservation(String reservationId) {
        StockReservation reservation = reservations.remove(reservationId);
        if (reservation == null) {
            return;
        }

        String productId = reservation.getProductId();
        reserved.put(productId, reserved.getOrDefault(productId, 0) - reservation.getQuantity());
    }

    public synchronized void cleanupExpiredReservations() {
        long now = System.currentTimeMillis();
        Iterator<Map.Entry<String, StockReservation>> iterator = reservations.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, StockReservation> entry = iterator.next();
            StockReservation reservation = entry.getValue();
            if (now > reservation.getExpiresAtMs()) {
                String productId = reservation.getProductId();
                reserved.put(productId, reserved.getOrDefault(productId, 0) - reservation.getQuantity());
                iterator.remove();
            }
        }
    }

    public String getId() {
        return id;
    }
}
