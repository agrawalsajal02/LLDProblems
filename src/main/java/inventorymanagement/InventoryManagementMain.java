package inventorymanagement;

import java.util.List;

public final class InventoryManagementMain {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager(List.of("EAST", "WEST"));
        AlertListener listener = new ConsoleAlertListener();

        manager.setLowStockAlert("EAST", "WIDGET", 10, listener);

        manager.addStock("EAST", "WIDGET", 15);
        manager.removeStock("EAST", "WIDGET", 6);
        manager.transfer("WIDGET", "EAST", "WEST", 5);

        System.out.println(manager.getWarehousesWithAvailability("WIDGET", 4));
    }
}
