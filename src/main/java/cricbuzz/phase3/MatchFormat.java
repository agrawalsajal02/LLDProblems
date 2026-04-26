package cricbuzz.phase3;

public enum MatchFormat {
    T20(20);

    private final int oversPerInnings;

    MatchFormat(int oversPerInnings) {
        this.oversPerInnings = oversPerInnings;
    }

    public int getOversPerInnings() {
        return oversPerInnings;
    }
}
