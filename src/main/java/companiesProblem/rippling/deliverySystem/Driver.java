package companiesProblem.rippling.deliverySystem;

public class Driver {
    public boolean isAvailable;
    String name;
    Integer hourlyRate;

    public Driver(String name, int hourlyRate) {
        this.name=name;
        this.hourlyRate=hourlyRate;
        isAvailable= true;
    }
}
