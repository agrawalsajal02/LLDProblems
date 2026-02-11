package amazonlocker.extensibility;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import amazonlocker.AccessToken;
import amazonlocker.Size;

public class ExtLocker {
    private final ExtCompartment[] compartments;
    private final Map<String, AccessToken> accessTokenMapping;
    private final Map<String, Reservation> reservations;
    private final Random random;

    public ExtLocker(ExtCompartment[] compartments) {
        this.compartments = compartments;
        this.accessTokenMapping = new HashMap<>();
        this.reservations = new HashMap<>();
        this.random = new Random();
    }

    // Extension 1: size fallback
    public String depositWithFallback(Size size) {
        ExtCompartment compartment = getAvailableCompartmentWithFallback(size);
        if (compartment == null) {
            throw new RuntimeException("No available compartment for size " + size);
        }
        compartment.open();
        compartment.markOccupied();
        AccessToken token = generateAccessToken(compartment);
        accessTokenMapping.put(token.getCode(), token);
        return token.getCode();
    }

    // Extension 2: maintenance/out of service
    private ExtCompartment getAvailableCompartment(Size size) {
        for (ExtCompartment c : compartments) {
            if (c.getSize() == size && c.isAvailable()) {
                return c;
            }
        }
        return null;
    }

    private ExtCompartment getAvailableCompartmentWithFallback(Size size) {
        Size[] order = {Size.SMALL, Size.MEDIUM, Size.LARGE};
        int start = 0;
        for (int i = 0; i < order.length; i++) {
            if (order[i] == size) {
                start = i;
                break;
            }
        }
        for (int i = start; i < order.length; i++) {
            ExtCompartment c = getAvailableCompartment(order[i]);
            if (c != null) {
                return c;
            }
        }
        return null;
    }

    // Extension 3: two-phase deposit (reserve -> confirm)
    public String reserveCompartment(Size size) {
        ExtCompartment compartment = getAvailableCompartment(size);
        if (compartment == null) {
            throw new RuntimeException("No available compartment of size " + size);
        }
        compartment.markReserved();
        compartment.open();
        String reservationId = String.format("R%06d", random.nextInt(1_000_000));
        reservations.put(reservationId, new Reservation(reservationId, compartment));
        return reservationId;
    }

    public String confirmDeposit(String reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation == null) {
            throw new RuntimeException("Invalid reservation");
        }
        ExtCompartment compartment = reservation.getCompartment();
        compartment.markOccupied();
        AccessToken token = generateAccessToken(compartment);
        accessTokenMapping.put(token.getCode(), token);
        reservations.remove(reservationId);
        return token.getCode();
    }

    private AccessToken generateAccessToken(ExtCompartment compartment) {
        String code = String.format("%06d", random.nextInt(1_000_000));
        Instant expiration = Instant.now().plus(7, ChronoUnit.DAYS);
        return new AccessToken(code, expiration, new amazonlocker.Compartment(compartment.getId(), compartment.getSize()));
    }
}
