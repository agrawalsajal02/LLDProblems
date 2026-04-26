package inventorymanagement.extensibility;

public interface InventoryHolder {
    void addStock(String productId, int quantity);
    boolean removeStock(String productId, int quantity);
    int getStock(String productId);
    boolean checkAvailability(String productId, int quantity);
}
