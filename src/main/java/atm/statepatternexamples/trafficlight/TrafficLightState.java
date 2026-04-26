package atm.statepatternexamples.trafficlight;

public abstract class TrafficLightState {
    public abstract void timerComplete(TrafficLight trafficLight);

    public abstract String color();
}
