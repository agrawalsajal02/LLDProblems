package solidprinciples.lsp.good;

public class ElectricCar implements Car {
    @Override
    public void accelerate() {
        System.out.println("Electric car accelerating");
    }
}
