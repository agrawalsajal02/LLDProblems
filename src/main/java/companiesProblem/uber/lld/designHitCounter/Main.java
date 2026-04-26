package companiesProblem.uber.lld.designHitCounter;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        HitCounter counter = new HitCounterByUrl();

        counter.hit("/home", 1);
        counter.hit("/home", 1);
        counter.hit("/home", 2);
        counter.hit("/about", 3);

        System.out.println(counter.getHits("/home", 2));    // 3
        System.out.println(counter.getHits("/about", 3));   // 1
        System.out.println(counter.getHits("/home", 300));  // 3
        System.out.println(counter.getHits("/home", 301));  // 1
    }
}
