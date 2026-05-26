package phonepe.service;

import phonepe.catalog.GameCatalog;
import phonepe.model.Leaderboard;
import phonepe.dto.LeaderboardRow;
import phonepe.dao.LeaderboardRepository;

import java.time.Clock;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class LeaderboardService {

    private static final int MIN_SCORE = 0;
    private static final int MAX_SCORE = 1000000000;

    private final LeaderboardRepository repository;
    private final GameCatalog gameCatalog;
    private final Clock clock;

    public LeaderboardService(
            LeaderboardRepository repository,
            GameCatalog gameCatalog,
            Clock clock
    ) {
        if (repository == null || gameCatalog == null || clock == null) {
            throw new IllegalArgumentException("Service dependencies cannot be null");
        }

        this.repository = repository;
        this.gameCatalog = gameCatalog;
        this.clock = clock;
    }

    public List<String> getSupportedGames() {
        return gameCatalog.getSupportedGames();
    }

    public String createLeaderboard(String gameId, long startEpochSeconds, long endEpochSeconds) {
        gameCatalog.validateGame(gameId);

        if (startEpochSeconds >= endEpochSeconds) {
            throw new IllegalArgumentException("start time should be before end time");
        }

        String leaderboardId = UUID.randomUUID().toString();

        Leaderboard leaderboard = new Leaderboard(
                leaderboardId,
                gameId,
                startEpochSeconds,
                endEpochSeconds
        );

        repository.save(leaderboard);
        return leaderboardId;
    }

    public void submitScore(String gameId, String userId, int score) {
        gameCatalog.validateGame(gameId);
        validateUserId(userId);
        validateScore(score);

        long now = clock.instant().getEpochSecond();

        List<Leaderboard> leaderboards = repository.findByGameId(gameId);

        for (Leaderboard leaderboard : leaderboards) {
            if (leaderboard.isActive(now)) {
                leaderboard.submitScore(userId, score, now);
            }
        }
    }

    public List<LeaderboardRow> getLeaderboard(String leaderboardId) {
        Leaderboard leaderboard = getLeaderboardOrThrow(leaderboardId);
        return leaderboard.getRankedList();
    }

    public List<LeaderboardRow> getTopScorers(String leaderboardId, int limit) {
        validateLimit(limit);

        Leaderboard leaderboard = getLeaderboardOrThrow(leaderboardId);
        return leaderboard.getTopRows(limit);
    }

    public List<LeaderboardRow> listPlayersPrev(
            String gameId,
            String leaderboardId,
            String userId,
            int nPlayers
    ) {
        gameCatalog.validateGame(gameId);
        validateUserId(userId);
        validateLimit(nPlayers);

        Leaderboard leaderboard = getLeaderboardOrThrow(leaderboardId);
        validateLeaderboardBelongsToGame(leaderboard, gameId);

        return leaderboard.getPlayersAbove(userId, nPlayers);
    }

    public List<LeaderboardRow> listPlayersNext(
            String gameId,
            String leaderboardId,
            String userId,
            int nPlayers
    ) {
        gameCatalog.validateGame(gameId);
        validateUserId(userId);
        validateLimit(nPlayers);

        Leaderboard leaderboard = getLeaderboardOrThrow(leaderboardId);
        validateLeaderboardBelongsToGame(leaderboard, gameId);

        return leaderboard.getPlayersBelow(userId, nPlayers);
    }

    private Leaderboard getLeaderboardOrThrow(String leaderboardId) {
        validateText(leaderboardId, "leaderboardId");

        return repository.findById(leaderboardId)
                .orElseThrow(() -> new NoSuchElementException("Leaderboard not found: " + leaderboardId));
    }

    private void validateLeaderboardBelongsToGame(Leaderboard leaderboard, String gameId) {
        if (!leaderboard.getGameId().equals(gameId)) {
            throw new IllegalArgumentException("Leaderboard does not belong to game: " + gameId);
        }
    }

    private void validateUserId(String userId) {
        validateText(userId, "userId");
    }

    private void validateScore(int score) {
        if (score < MIN_SCORE || score > MAX_SCORE) {
            throw new IllegalArgumentException("Score should be between 0 and 1 billion");
        }
    }

    private void validateLimit(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit cannot be negative");
        }
    }

    private void validateText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
    }
}