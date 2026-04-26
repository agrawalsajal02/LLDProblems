package atm.statepatternexamples.trafficlight;

public final class RedState extends TrafficLightState {
    @Override
    public void timerComplete(TrafficLight trafficLight) {
        // Red ke baad Green me jaana fixed transition hai, isliye state hi next state decide karti hai.
        trafficLight.setCurrentState(new GreenState());
    }

    @Override
    public String color() {
        return "RED";
    }
}
