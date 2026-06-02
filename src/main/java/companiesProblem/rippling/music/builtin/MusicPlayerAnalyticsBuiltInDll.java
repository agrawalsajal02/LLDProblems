package companiesProblem.rippling.music.builtin;

import java.util.*;

public class MusicPlayerAnalyticsBuiltInDll {

    private int nextSongId = 1;

    // uniqueListenerRanking: used by print_summary_for_unique_listeners().
    // totalPlayRanking: used by print_summary_for_top_k().
    // Both are updated in addSong()/playSong() so queries do not need sorting again.
    private final TreeSet<Song> uniqueListenerRanking = new TreeSet<>((a, b) -> {
        int countCompare = Integer.compare(b.uniqueListenerCount(), a.uniqueListenerCount());
        if (countCompare != 0) return countCompare;

        int titleCompare = a.title.compareTo(b.title);
        if (titleCompare != 0) return titleCompare;

        return Integer.compare(a.id, b.id);
    });

    private final TreeSet<Song> totalPlayRanking = new TreeSet<>((a, b) -> {
        int countCompare = Integer.compare(b.totalPlayCount, a.totalPlayCount);
        if (countCompare != 0) return countCompare;

        int titleCompare = a.title.compareTo(b.title);
        if (titleCompare != 0) return titleCompare;

        return Integer.compare(a.id, b.id);
    });

    // userMap: used by playSong() to update history and last_three_played_songs() to read it.
    private final Map<Integer, UserHistory> userMap = new HashMap<>();

    // songMap: used by addSong() and playSong(); not used by summary/history query methods.
    private final Map<Integer, Song> songMap = new HashMap<>();

    public int addSong(String songTitle) {
        Song song = new Song(nextSongId++, songTitle);
        songMap.put(song.id, song);
        uniqueListenerRanking.add(song);
        totalPlayRanking.add(song);
        return song.id;
    }

    public void playSong(int songId, int userId) {
        Song song = songMap.get(songId);
        if (song == null) {
            throw new IllegalArgumentException("Invalid song id: " + songId);
        }

        totalPlayRanking.remove(song);
        song.totalPlayCount++;
        totalPlayRanking.add(song);

        if (!song.uniqueListeners.contains(userId)) {
            uniqueListenerRanking.remove(song);
            song.uniqueListeners.add(userId);
            uniqueListenerRanking.add(song);
        }

        UserHistory history = userMap.computeIfAbsent(userId, id -> new UserHistory());
        history.markRecentlyPlayed(songId);
    }

    public void print_summary_for_unique_listeners() {
        for (Song song : uniqueListenerRanking) {
            printUniqueListenerSong(song);
        }
    }

    public void print_summary_for_top_k(int k) {
        if (k <= 0) {
            return;
        }

        int printed = 0;
        for (Song song : totalPlayRanking) {
            if (printed == k) {
                break;
            }
            printTotalPlaySong(song);
            printed++;
        }
    }

    private void printUniqueListenerSong(Song song) {
        System.out.println(song.title + " (" + song.uniqueListenerCount() + " unique listeners)");
    }

    private void printTotalPlaySong(Song song) {
        System.out.println(song.title + " (" + song.totalPlayCount + " plays)");
    }

    public List<Integer> last_three_played_songs(int userId) {
        UserHistory history = userMap.get(userId);
        if (history == null) {
            return new ArrayList<>();
        }
        return history.lastK(3);
    }

    public List<Integer> lastThreePlayedSongs(int userId) {
        return last_three_played_songs(userId);
    }

    private static final class Song {
        private final int id;
        private final String title;
        private final Set<Integer> uniqueListeners = new HashSet<>();
        private int totalPlayCount;

        private Song(int id, String title) {
            this.id = id;
            this.title = title;
        }

        private int uniqueListenerCount() {
            return uniqueListeners.size();
        }
    }

    private static final class UserHistory {
        // Java's LinkedHashMap internally maintains a doubly linked list.
        // accessOrder=true moves a song to the most-recent position whenever it is played again.
        private final LinkedHashMap<Integer, Boolean> recentSongs = new LinkedHashMap<>(16, 0.75f, true);

        private void markRecentlyPlayed(int songId) {
            recentSongs.put(songId, true);
        }

        private List<Integer> lastK(int k) {
            List<Integer> answer = new ArrayList<>();

            for (int songId : recentSongs.reversed().keySet()) {
                if (answer.size() == k) {
                    break;
                }
                answer.add(songId);
            }

            return answer;
        }
    }

    public static void main(String[] args) {
        MusicPlayerAnalyticsBuiltInDll player = new MusicPlayerAnalyticsBuiltInDll();

        int songA = player.addSong("Song A");
        int songB = player.addSong("Song B");
        int songC = player.addSong("Song C");
        int anotherSongB = player.addSong("Song B");

        player.playSong(songA, 101);
        player.playSong(songA, 101);
        player.playSong(songA, 102);
        player.playSong(songB, 101);
        player.playSong(songC, 101);
        player.playSong(songB, 101);
        player.playSong(anotherSongB, 101);

        System.out.println("All songs by unique listeners:");
        player.print_summary_for_unique_listeners();

        System.out.println("Top 2 songs:");
        player.print_summary_for_top_k(2);

        System.out.println("Last 3 unique songs for user 101: " + player.last_three_played_songs(101));
    }
}
