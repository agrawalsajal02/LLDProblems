package companiesProblem.uber.lld.inventoryManagement;

import java.math.BigDecimal;

public final class Car {
    private final String carId;
    private final String licensePlate;
    private final String make;
    private final String model;
    private final String city;
    private final CarCategory category;
    private final BigDecimal hourlyRate;
    private CarStatus status;

    public Car(
        String carId,
        String licensePlate,
        String make,
        String model,
        String city,
        CarCategory category,
        BigDecimal hourlyRate,
        CarStatus status
    ) {
        if (hourlyRate == null || hourlyRate.signum() < 0) {
            throw new IllegalArgumentException("Hourly rate must be non-negative");
        }
        this.carId = carId;
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.city = city;
        this.category = category;
        this.hourlyRate = hourlyRate;
        this.status = status;
    }

    public String getCarId() {
        return carId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getCity() {
        return city;
    }

    public CarCategory getCategory() {
        return category;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void updateStatus(CarStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Car{" +
            "carId='" + carId + '\'' +
            ", licensePlate='" + licensePlate + '\'' +
            ", make='" + make + '\'' +
            ", model='" + model + '\'' +
            ", city='" + city + '\'' +
            ", category=" + category +
            ", hourlyRate=" + hourlyRate +
            ", status=" + status +
            '}';
    }
}
