package phonepe.catalog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameCatalog {

    private final Set<String> games = new HashSet<>();

    public GameCatalog(Collection<String> gameIds) {
        if (gameIds == null || gameIds.isEmpty()) {
            throw new IllegalArgumentException("At least one game should be supported");
        }

        for (String gameId : gameIds) {
            addGame(gameId);
        }
    }

    public boolean hasGame(String gameId) {
        return games.contains(gameId);
    }

    public List<String> getSupportedGames() {
        return new ArrayList<>(games);
    }

    public void validateGame(String gameId) {
        validateText(gameId, "gameId");

        if (!hasGame(gameId)) {
            throw new IllegalArgumentException("Unsupported game: " + gameId);
        }
    }

    private void addGame(String gameId) {
        validateText(gameId, "gameId");
        games.add(gameId);
    }

    private void validateText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
    }
}