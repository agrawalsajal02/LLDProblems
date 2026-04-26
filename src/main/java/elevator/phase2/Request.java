package elevator.phase2;

import java.util.Objects;

public final class Request {
    private final int floor;
    private final RequestType type;

    public Request(int floor, RequestType type) {
        this.floor = floor;
        this.type = type;
    }

    public int getFloor() {
        return floor;
    }

    public RequestType getType() {
        return type;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Request)) {
            return false;
        }
        Request that = (Request) other;
        return floor == that.floor && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(floor, type);
    }
}
