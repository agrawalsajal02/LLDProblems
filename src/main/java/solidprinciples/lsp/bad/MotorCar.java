package solidprinciples.lsp.bad;

public class MotorCar implements Car {
    @Override
    public void turnOnEngine() {
        System.out.println("Engine on");
    }

    @Override
    public void accelerate() {
        System.out.println("Motor car accelerating");
    }
}
