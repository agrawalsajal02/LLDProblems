package phonepe.model;

import phonepe.dto.LeaderboardRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeSet;

public class Leaderboard {

    private final String id;
    private final String gameId;
    private final long startEpochSeconds;
    private final long endEpochSeconds;

    private final Map<String, ScoreEntry> bestScoreByUser = new HashMap<>();
    private final TreeSet<ScoreEntry> sortedScores = new TreeSet<>(ScoreEntry.BEST_SCORE_FIRST);

    public Leaderboard(String id, String gameId, long startEpochSeconds, long endEpochSeconds) {
        checkText(id, "id");
        checkText(gameId, "gameId");

        if (startEpochSeconds >= endEpochSeconds) {
            throw new IllegalArgumentException("start time should be before end time");
        }

        this.id = id;
        this.gameId = gameId;
        this.startEpochSeconds = startEpochSeconds;
        this.endEpochSeconds = endEpochSeconds;
    }

    public String getId() {
        return id;
    }

    public String getGameId() {
        return gameId;
    }

    public boolean isActive(long epochSeconds) {
        return epochSeconds >= startEpochSeconds && epochSeconds < endEpochSeconds;
    }

    public synchronized boolean submitScore(String userId, int score, long submittedAt) {
        ScoreEntry oldEntry = bestScoreByUser.get(userId);

        if (oldEntry != null && oldEntry.getScore() >= score) {
            return false;
        }

        if (oldEntry != null) {
            sortedScores.remove(oldEntry);
        }

        ScoreEntry newEntry = new ScoreEntry(userId, score, submittedAt);

        bestScoreByUser.put(userId, newEntry);
        sortedScores.add(newEntry);

        return true;
    }

    public synchronized List<LeaderboardRow> getRankedList() {
        return buildRows(sortedScores.size());
    }

    public synchronized List<LeaderboardRow> getTopRows(int limit) {
        return buildRows(limit);
    }

    public synchronized List<LeaderboardRow> getPlayersAbove(String userId, int count) {
        List<LeaderboardRow> rows = buildRows(sortedScores.size());
        int index = findUserIndex(rows, userId);

        int from = Math.max(0, index - count);
        int to = index;

        return new ArrayList<>(rows.subList(from, to));
    }

    public synchronized List<LeaderboardRow> getPlayersBelow(String userId, int count) {
        List<LeaderboardRow> rows = buildRows(sortedScores.size());
        int index = findUserIndex(rows, userId);

        int from = index + 1;
        int to = Math.min(rows.size(), index + 1 + count);

        return new ArrayList<>(rows.subList(from, to));
    }

    private List<LeaderboardRow> buildRows(int limit) {
        List<LeaderboardRow> rows = new ArrayList<>();

        int rank = 1;

        for (ScoreEntry entry : sortedScores) {
            if (rows.size() == limit) {
                break;
            }

            rows.add(new LeaderboardRow(rank, entry.getUserId(), entry.getScore()));
            rank++;
        }

        return rows;
    }

    private int findUserIndex(List<LeaderboardRow> rows, String userId) {
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i).getUserId().equals(userId)) {
                return i;
            }
        }

        throw new NoSuchElementException("User not found in leaderboard: " + userId);
    }

    private void checkText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
    }
}