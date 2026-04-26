package cricbuzz.phase1;

public final class Main {
    public static void main(String[] args) {
        Innings innings = new Innings(new TeamScore("India"), 2, new OutcomeGenerator());
        System.out.println(innings.play());
    }
}
