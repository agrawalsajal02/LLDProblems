package atm.statepatternexamples.trafficlight;

public final class TrafficLight {
    private TrafficLightState currentState;

    public TrafficLight() {
        this.currentState = new RedState();
    }

    public void timerComplete() {
        currentState.timerComplete(this);
    }

    public String currentColor() {
        return currentState.color();
    }

    public void setCurrentState(TrafficLightState currentState) {
        this.currentState = currentState;
    }
}
