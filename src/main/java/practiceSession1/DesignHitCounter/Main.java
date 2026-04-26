package practiceSession1.DesignHitCounter;

public class Main {
    static void main() {
        HitCounter hitCounter= new HitCounter(300);
        hitCounter.hit("/home",1);
        hitCounter.hit("/etc",2);
        hitCounter.hit("/home",1);

        System.out.println(hitCounter.getHits("/home",4));
    }
}
