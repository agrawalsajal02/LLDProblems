package companiesProblem.uber.lld.inventoryManagement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CarInventoryService {
    private final Map<String, CarAvailabilityCalendar> calendarsByCarId;

    public CarInventoryService() {
        this.calendarsByCarId = new HashMap<>();
    }

    public void addCar(Car car) {
        calendarsByCarId.put(car.getCarId(), new CarAvailabilityCalendar(car));
    }

    public void updateCarStatus(String carId, CarStatus status) {
        getCalendar(carId).getCar().updateStatus(status);
    }

    public List<Car> findAvailableCars(CarSearchCriteria criteria, TimeSlot requestedSlot) {
        List<Car> availableCars = new ArrayList<>();
        for (CarAvailabilityCalendar calendar : calendarsByCarId.values()) {
            Car car = calendar.getCar();
            if (!car.getCity().equalsIgnoreCase(criteria.getCity())) {
                continue;
            }
            if (car.getCategory() != criteria.getCategory()) {
                continue;
            }
            if (calendar.isAvailable(requestedSlot)) {
                availableCars.add(car);
            }
        }

        availableCars.sort(Comparator.comparing(Car::getHourlyRate).thenComparing(Car::getCarId));
        return availableCars;
    }

    public void blockCarForReservation(Reservation reservation) {
        CarAvailabilityCalendar calendar = getCalendar(reservation.getCar().getCarId());
        if (!calendar.isAvailable(reservation.getReservedSlot())) {
            throw new ReservationConflictException("Car is no longer available: " + reservation.getCar().getCarId());
        }
        calendar.addReservation(reservation);
    }

    public Car getCar(String carId) {
        return getCalendar(carId).getCar();
    }

    private CarAvailabilityCalendar getCalendar(String carId) {
        CarAvailabilityCalendar calendar = calendarsByCarId.get(carId);
        if (calendar == null) {
            throw new NotFoundException("Car not found: " + carId);
        }
        return calendar;
    }
}
