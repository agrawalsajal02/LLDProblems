package phonepe.dao;

import phonepe.model.Leaderboard;

import java.util.List;
import java.util.Optional;

public interface LeaderboardRepository {

    void save(Leaderboard leaderboard);

    Optional<Leaderboard> findById(String leaderboardId);

    List<Leaderboard> findByGameId(String gameId);
}