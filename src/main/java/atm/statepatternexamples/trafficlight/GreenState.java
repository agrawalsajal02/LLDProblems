package atm.statepatternexamples.trafficlight;

public final class GreenState extends TrafficLightState {
    @Override
    public void timerComplete(TrafficLight trafficLight) {
        trafficLight.setCurrentState(new YellowState());
    }

    @Override
    public String color() {
        return "GREEN";
    }
}
