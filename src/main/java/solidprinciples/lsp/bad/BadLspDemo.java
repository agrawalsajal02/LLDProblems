package solidprinciples.lsp.bad;

public final class BadLspDemo {
    public static void main(String[] args) {
        Car car = new ElectricCar();
        car.turnOnEngine();
    }
}
