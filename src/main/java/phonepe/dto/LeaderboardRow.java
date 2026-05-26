package phonepe.dto;
public class LeaderboardRow {

    private final int rank;
    private final String userId;
    private final int score;

    public LeaderboardRow(int rank, String userId, int score) {
        this.rank = rank;
        this.userId = userId;
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public String getUserId() {
        return userId;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Rank " + rank + " | " + userId + " | score=" + score;
    }
}