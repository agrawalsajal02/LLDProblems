package companiesProblem.rippling.ruleengine.expense;

import java.util.Map;

public final class Expense {
    private final String expenseId;
    private final String tripId;
    private final String itemId;
    private final String expenseType;
    private final double amountInUsd;
    private final String vendorType;
    private final String vendorName;

    public Expense(String expenseId, String tripId, String itemId, String expenseType,
                   double amountInUsd, String vendorType, String vendorName) {
        this.expenseId = expenseId;
        this.tripId = tripId;
        this.itemId = itemId;
        this.expenseType = expenseType;
        this.amountInUsd = amountInUsd;
        this.vendorType = vendorType;
        this.vendorName = vendorName;
    }

    public static Expense fromMap(Map<String, String> row) {
        return new Expense(
                row.get("expense_id"),
                row.get("trip_id"),
                row.getOrDefault("item_id", ""),
                row.get("expense_type"),
                Double.parseDouble(row.getOrDefault("amount_usd", "0")),
                firstPresent(row, "vendor_type", "seller_type"),
                firstPresent(row, "vendor_name", "seller_name")
        );
    }

    private static String firstPresent(Map<String, String> row, String firstKey, String secondKey) {
        String value = row.get(firstKey);
        if (value != null) {
            return value;
        }
        return row.getOrDefault(secondKey, "");
    }

    public String getExpenseId() {
        return expenseId;
    }

    public String getTripId() {
        return tripId;
    }

    public String getItemId() {
        return itemId;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public double getAmountInUsd() {
        return amountInUsd;
    }

    public String getVendorType() {
        return vendorType;
    }

    public String getVendorName() {
        return vendorName;
    }
}
