package companiesProblem.uber.lld.inventoryManagement;

public final class CarSearchCriteria {
    private final String city;
    private final CarCategory category;

    public CarSearchCriteria(String city, CarCategory category) {
        this.city = city;
        this.category = category;
    }

    public String getCity() {
        return city;
    }

    public CarCategory getCategory() {
        return category;
    }
}
