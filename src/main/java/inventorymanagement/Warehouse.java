package inventorymanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Warehouse {
    private final String id;
    private final Map<String, Integer> inventory;
    private final Map<String, List<AlertConfig>> alertConfigs;

    public Warehouse(String id) {
        this.id = id;
        this.inventory = new HashMap<>();
        this.alertConfigs = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void addStock(String productId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        List<AlertToFire> alertsToFire;
        synchronized (this) {
            int currentQty = inventory.getOrDefault(productId, 0);
            int newQty = currentQty + quantity;
            inventory.put(productId, newQty);
            alertsToFire = getAlertsToFire(productId, currentQty, newQty);
        }

        fireAlerts(alertsToFire);
    }

    public boolean removeStock(String productId, int quantity) {
        if (quantity <= 0) {
            return false;
        }

        List<AlertToFire> alertsToFire;
        synchronized (this) {
            int currentQty = inventory.getOrDefault(productId, 0);
            if (currentQty < quantity) {
                return false;
            }

            int newQty = currentQty - quantity;
            inventory.put(productId, newQty);
            alertsToFire = getAlertsToFire(productId, currentQty, newQty);
        }

        fireAlerts(alertsToFire);
        return true;
    }

    public synchronized int getStock(String productId) {
        return inventory.getOrDefault(productId, 0);
    }

    public synchronized boolean checkAvailability(String productId, int quantity) {
        if (quantity <= 0) {
            return false;
        }
        return inventory.getOrDefault(productId, 0) >= quantity;
    }

    public synchronized void setLowStockAlert(String productId, int threshold, AlertListener listener) {
        if (threshold <= 0) {
            throw new IllegalArgumentException("Threshold must be positive");
        }
        if (listener == null) {
            throw new IllegalArgumentException("Listener cannot be null");
        }

        alertConfigs.computeIfAbsent(productId, ignored -> new ArrayList<>())
            .add(new AlertConfig(threshold, listener));
    }

    int getStockInternal(String productId) {
        return inventory.getOrDefault(productId, 0);
    }

    List<AlertToFire> setStockInternal(String productId, int newQty) {
        int previousQty = inventory.getOrDefault(productId, 0);
        inventory.put(productId, newQty);
        return getAlertsToFire(productId, previousQty, newQty);
    }

    private List<AlertToFire> getAlertsToFire(String productId, int previousQty, int newQty) {
        List<AlertConfig> configs = alertConfigs.get(productId);
        if (configs == null) {
            return List.of();
        }

        List<AlertToFire> alertsToFire = new ArrayList<>();
        for (AlertConfig config : configs) {
            // Hinglish: alert tabhi fire karo jab stock threshold ke upar se neeche cross kare.
            if (previousQty >= config.getThreshold() && newQty < config.getThreshold()) {
                alertsToFire.add(new AlertToFire(config.getListener(), id, productId, newQty));
            }
        }
        return alertsToFire;
    }

    static void fireAlerts(List<AlertToFire> alerts) {
        for (AlertToFire alert : alerts) {
            alert.listener.onLowStock(alert.warehouseId, alert.productId, alert.quantity);
        }
    }
}
