package solidprinciples.isp.good;

public class BearCarer implements BearCleaner, BearFeeder {
    @Override
    public void washTheBear() {
        System.out.println("Bear washed");
    }

    @Override
    public void feedTheBear() {
        System.out.println("Bear fed");
    }
}
