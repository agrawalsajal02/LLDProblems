# Inventory Management Extensibility

## 1) Reservation based stock hold
- `ReservableWarehouse` adds `reserved` quantities and reservation records.
- Use this when checkout is slow and you want to avoid overselling during payment.

## 2) In-transit inventory
- `TransferInventory` models stock that is no longer in source warehouse and not yet available at destination.
- It implements `InventoryHolder` so transfer stock can be treated like another inventory container.
