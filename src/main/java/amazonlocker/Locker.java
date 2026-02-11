package amazonlocker;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Locker {
    private final Compartment[] compartments;
    private final Map<String, AccessToken> accessTokenMapping;
    private final Random random;

    public Locker(Compartment[] compartments) {
        this.compartments = compartments;
        this.accessTokenMapping = new HashMap<>();
        this.random = new Random();
    }

    public String depositPackage(Size size) {
        Compartment compartment = getAvailableCompartment(size);
        if (compartment == null) {
            throw new RuntimeException("No available compartment of size " + size);
        }

        compartment.open();
        compartment.markOccupied();
        AccessToken token = generateAccessToken(compartment);
        accessTokenMapping.put(token.getCode(), token);

        return token.getCode();
    }

    public void pickup(String tokenCode) {
        if (tokenCode == null || tokenCode.isEmpty()) {
            throw new RuntimeException("Invalid access token code");
        }

        AccessToken token = accessTokenMapping.get(tokenCode);
        if (token == null) {
            throw new RuntimeException("Invalid access token code");
        }

        if (token.isExpired()) {
            throw new RuntimeException("Access token has expired");
        }

        Compartment compartment = token.getCompartment();
        compartment.open();
        clearDeposit(token);
    }

    public void openExpiredCompartments() {
        for (AccessToken token : accessTokenMapping.values()) {
            if (token.isExpired()) {
                Compartment compartment = token.getCompartment();
                compartment.open();
            }
        }
    }

    private Compartment getAvailableCompartment(Size size) {
        for (Compartment c : compartments) {
            if (c.getSize() == size && !c.isOccupied()) {
                return c;
            }
        }
        return null;
    }

    private AccessToken generateAccessToken(Compartment compartment) {
        String code = generateAccessCode();
        Instant expiration = Instant.now().plus(7, ChronoUnit.DAYS);
        return new AccessToken(code, expiration, compartment);
    }

    private String generateAccessCode() {
        // Current approach (simple 6-digit code, may collide rarely)
        String code = String.format("%06d", random.nextInt(1_000_000));

        // Option 1: UUID (very unique but long)
        // String code = java.util.UUID.randomUUID().toString();

        // Option 2: SecureRandom 6-digit + collision check (needs active code set)
        // java.security.SecureRandom sr = new java.security.SecureRandom();
        // String code = String.format("%06d", sr.nextInt(1_000_000));
        // if (accessTokenMapping.containsKey(code)) { retry/generate again; }

        // Option 3: Time-based short code (not guaranteed, but low collision)
        // String code = String.format("%06d", (System.currentTimeMillis() % 1_000_000));

        return code;
    }

    private void clearDeposit(AccessToken token) {
        Compartment compartment = token.getCompartment();
        compartment.markFree();
        accessTokenMapping.remove(token.getCode());
    }
}
