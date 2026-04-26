package inventorymanagement;

final class AlertToFire {
    final AlertListener listener;
    final String warehouseId;
    final String productId;
    final int quantity;

    AlertToFire(AlertListener listener, String warehouseId, String productId, int quantity) {
        this.listener = listener;
        this.warehouseId = warehouseId;
        this.productId = productId;
        this.quantity = quantity;
    }
}
