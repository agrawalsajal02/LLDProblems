package phonepe.model;
import java.util.Comparator;

public class ScoreEntry {

    public static final Comparator<ScoreEntry> BEST_SCORE_FIRST =
            Comparator.comparingInt(ScoreEntry::getScore).reversed()
                    .thenComparingLong(ScoreEntry::getSubmittedAt)
                    .thenComparing(ScoreEntry::getUserId);

    private final String userId;
    private final int score;
    private final long submittedAt;

    public ScoreEntry(String userId, int score, long submittedAt) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("userId cannot be empty");
        }

        this.userId = userId;
        this.score = score;
        this.submittedAt = submittedAt;
    }

    public String getUserId() {
        return userId;
    }

    public int getScore() {
        return score;
    }

    public long getSubmittedAt() {
        return submittedAt;
    }
}