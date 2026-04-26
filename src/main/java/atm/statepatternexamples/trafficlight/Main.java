package atm.statepatternexamples.trafficlight;

public final class Main {
    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();
        System.out.println(trafficLight.currentColor());
        trafficLight.timerComplete();
        System.out.println(trafficLight.currentColor());
        trafficLight.timerComplete();
        System.out.println(trafficLight.currentColor());
        trafficLight.timerComplete();
        System.out.println(trafficLight.currentColor());
    }
}
