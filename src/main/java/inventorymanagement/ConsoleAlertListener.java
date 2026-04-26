package inventorymanagement;

public class ConsoleAlertListener implements AlertListener {
    @Override
    public void onLowStock(String warehouseId, String productId, int currentQuantity) {
        System.out.println(
            "Low stock alert -> warehouse=" + warehouseId
                + ", product=" + productId
                + ", qty=" + currentQuantity
        );
    }
}
