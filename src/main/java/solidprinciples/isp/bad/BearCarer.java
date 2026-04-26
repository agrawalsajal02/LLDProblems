package solidprinciples.isp.bad;

public class BearCarer implements BearKeeper {
    @Override
    public void washTheBear() {
        System.out.println("Bear washed");
    }

    @Override
    public void feedTheBear() {
        System.out.println("Bear fed");
    }

    @Override
    public void petTheBear() {
        throw new UnsupportedOperationException("Bear carer should not pet the bear");
    }
}
