package phonepe;

import phonepe.catalog.GameCatalog;
import phonepe.dao.LeaderboardRepository;
import phonepe.dao.impl.InMemoryLeaderboardRepository;
import phonepe.dto.LeaderboardRow;
import phonepe.service.LeaderboardService;

import java.time.Clock;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeaderboardMain {

    public static void main(String[] args) {
        Set<String> games = new HashSet<>(Arrays.asList("cricket", "chess", "carrom"));

        GameCatalog gameCatalog = new GameCatalog(games);
        LeaderboardRepository repository = new InMemoryLeaderboardRepository();
        LeaderboardService service = new LeaderboardService(repository, gameCatalog, Clock.systemUTC());

        long now = Instant.now().getEpochSecond();

        String dailyBoard = service.createLeaderboard("cricket", now, now + 5000);
        String weeklyBoard = service.createLeaderboard("cricket", now, now + 10000);
        String oldBoard = service.createLeaderboard("cricket", now - 10000, now - 5000);

        service.submitScore("cricket", "u1", 100);
        service.submitScore("cricket", "u2", 500);
        service.submitScore("cricket", "u3", 300);
        service.submitScore("cricket", "u4", 700);
        service.submitScore("cricket", "u5", 200);

        service.submitScore("cricket", "u2", 400);
        service.submitScore("cricket", "u1", 800);

        System.out.println("Supported games:");
        System.out.println(service.getSupportedGames());

        System.out.println("\nDaily leaderboard:");
        printRows(service.getLeaderboard(dailyBoard));

        System.out.println("\nWeekly leaderboard:");
        printRows(service.getLeaderboard(weeklyBoard));

        System.out.println("\nOld leaderboard is still accessible:");
        printRows(service.getLeaderboard(oldBoard));

        System.out.println("\nTop 3:");
        printRows(service.getTopScorers(dailyBoard, 3));

        System.out.println("\n2 players above u3:");
        printRows(service.listPlayersPrev("cricket", dailyBoard, "u3", 2));

        System.out.println("\n2 players below u3:");
        printRows(service.listPlayersNext("cricket", dailyBoard, "u3", 2));
    }

    private static void printRows(List<LeaderboardRow> rows) {
        for (LeaderboardRow row : rows) {
            System.out.println(row);
        }
    }
}