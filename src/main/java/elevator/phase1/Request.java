package elevator.phase1;

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

//    Iska matlab:
//
//    same object hai toh turant true
//    wrong type hai toh false
//    warna fields compare karo
//    Ye Java mein normal boilerplate hai.
    @Override
    public boolean equals(Object other) {
//        Agar dono references exactly same object ko point kar rahe hain, toh: , ek fast shortcut hai.
//        if (this == other) {
//            return true;
//        }
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
