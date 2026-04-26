package solidprinciples.lsp.good;

public final class GoodLspDemo {
    public static void main(String[] args) {
        Car first = new MotorCar();
        Car second = new ElectricCar();

        first.accelerate();
        second.accelerate();
    }
}
