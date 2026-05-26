package phonepe.dao.impl;
import phonepe.model.Leaderboard;
import phonepe.dao.LeaderboardRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryLeaderboardRepository implements LeaderboardRepository {

    private final Map<String, Leaderboard> boardsById = new ConcurrentHashMap<>();
    private final Map<String, Set<String>> boardIdsByGame = new ConcurrentHashMap<>();

    @Override
    public void save(Leaderboard leaderboard) {
        if (leaderboard == null) {
            throw new IllegalArgumentException("leaderboard cannot be null");
        }

        boardsById.put(leaderboard.getId(), leaderboard);

        boardIdsByGame
                .computeIfAbsent(leaderboard.getGameId(), key -> ConcurrentHashMap.newKeySet())
                .add(leaderboard.getId());
    }

    @Override
    public Optional<Leaderboard> findById(String leaderboardId) {
        return Optional.ofNullable(boardsById.get(leaderboardId));
    }

    @Override
    public List<Leaderboard> findByGameId(String gameId) {
        Set<String> ids = boardIdsByGame.getOrDefault(gameId, Collections.emptySet());
        List<Leaderboard> result = new ArrayList<>();

        for (String id : ids) {
            Leaderboard leaderboard = boardsById.get(id);

            if (leaderboard != null) {
                result.add(leaderboard);
            }
        }

        return result;
    }
}