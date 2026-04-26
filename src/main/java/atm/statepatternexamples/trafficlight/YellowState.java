package atm.statepatternexamples.trafficlight;

public final class YellowState extends TrafficLightState {
    @Override
    public void timerComplete(TrafficLight trafficLight) {
        trafficLight.setCurrentState(new RedState());
    }

    @Override
    public String color() {
        return "YELLOW";
    }
}
