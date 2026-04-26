package solidprinciples.lsp.good;

public class MotorCar implements Car, EnginePowered {
    @Override
    public void turnOnEngine() {
        System.out.println("Engine on");
    }

    @Override
    public void accelerate() {
        System.out.println("Motor car accelerating");
    }
}
