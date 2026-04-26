package inventorymanagement.extensibility;

public class TransferInventory implements InventoryHolder {
    private final String id;
    private final String productId;
    private final String fromWarehouseId;
    private final String toWarehouseId;
    private final long createdAtMs;
    private int quantity;

    public TransferInventory(String id, String productId, int quantity, String fromWarehouseId, String toWarehouseId) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.fromWarehouseId = fromWarehouseId;
        this.toWarehouseId = toWarehouseId;
        this.createdAtMs = System.currentTimeMillis();
    }

    @Override
    public void addStock(String productId, int quantity) {
        if (!this.productId.equals(productId)) {
            throw new IllegalArgumentException("Transfer only tracks one product");
        }
        this.quantity += quantity;
    }

    @Override
    public boolean removeStock(String productId, int quantity) {
        if (!this.productId.equals(productId) || this.quantity < quantity) {
            return false;
        }
        this.quantity -= quantity;
        return true;
    }

    @Override
    public int getStock(String productId) {
        return this.productId.equals(productId) ? quantity : 0;
    }

    @Override
    public boolean checkAvailability(String productId, int quantity) {
        return this.productId.equals(productId) && this.quantity >= quantity;
    }

    public String getId() {
        return id;
    }

    public String getFromWarehouseId() {
        return fromWarehouseId;
    }

    public String getToWarehouseId() {
        return toWarehouseId;
    }

    public long getCreatedAtMs() {
        return createdAtMs;
    }
}
