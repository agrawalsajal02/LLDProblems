package cricbuzz;

public enum MatchFormat {
    T20(20),
    ODI(50);

    private final int oversPerInnings;

    MatchFormat(int oversPerInnings) {
        this.oversPerInnings = oversPerInnings;
    }

    public int getOversPerInnings() {
        return oversPerInnings;
    }
}
