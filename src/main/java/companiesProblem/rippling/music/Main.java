package companiesProblem.rippling.music;

import java.util.*;

class SongAnalytics {
    private int nextId = 1;

    private final Map<Integer, String> idToTitle = new HashMap<>();

    // unique listeners per song
    private final Map<Integer, Set<Integer>> songToUsers = new HashMap<>();
    private final Map<Integer, Integer> uniqueCount = new HashMap<>();

    // leaderboard: count -> set of songIds ordered by title asc, then id
    private final NavigableMap<Integer, TreeSet<Integer>> countToSongs =
            new TreeMap<>(Comparator.reverseOrder());

    // per-user LRU (song recency unique)
    private static class Node {
        int songId;
        Node prev, next;
        Node(int s)
        {
            songId = s;
        }
    }
    private static class DLL {
        Node head = new Node(-1),
                tail = new Node(-1);

        DLL() { head.next = tail;
            tail.prev = head; }

        void addFront(Node n) { n.next=head.next;
            n.prev=head;
            head.next.prev=n;
            head.next=n; }

        void remove(Node n) { n.prev.next = n.next;
            n.next.prev = n.prev; }

        Node front() { return head.next; }
    }
    private final Map<Integer, DLL> userList = new HashMap<>();
    private final Map<Integer, Map<Integer, Node>> userIndex = new HashMap<>();

    public int add_song(String name) {
        int id = nextId++;
        idToTitle.put(id, name);
        songToUsers.put(id, new HashSet<>());
        uniqueCount.put(id, 0);
        countToSongs.computeIfAbsent(0, k -> new TreeSet<>(
                (a,b) -> {
                    int t = idToTitle.get(a).compareTo(idToTitle.get(b));
                    return t != 0 ? t : Integer.compare(a,b);
                }
        )).add(id);
        return id;
    }

    public void play_song(int songId, int userId) {
        if (!idToTitle.containsKey(songId)) {
            System.out.println("Error: Song ID " + songId + " does not exist.");
            return;
        }
        // recency LRU per user (unique by song)
        DLL dll = userList.computeIfAbsent(userId, k -> new DLL());
        Map<Integer, Node> idx = userIndex.computeIfAbsent(userId, k -> new HashMap<>());
        Node node = idx.get(songId);
        if (node != null) {
            dll.remove(node);
            dll.addFront(node);
        } else {
            node = new Node(songId);
            dll.addFront(node);
            idx.put(songId, node);
        }

        // unique listener count once per song
        Set<Integer> listeners = songToUsers.get(songId);
        if (listeners.add(userId)) {
            int oldC = uniqueCount.get(songId);
            int newC = oldC + 1;
            uniqueCount.put(songId, newC);

            TreeSet<Integer> oldBucket = countToSongs.get(oldC);
            oldBucket.remove(songId);
            if (oldBucket.isEmpty()) countToSongs.remove(oldC);

            countToSongs
                    .computeIfAbsent(newC, k -> new TreeSet<>(
                            (a,b) -> {
                                int t = idToTitle.get(a).compareTo(idToTitle.get(b));
                                return t != 0 ? t : Integer.compare(a,b);
                            }
                    )).add(songId);
        }
    }

    public void print_analytics() {
        for (Map.Entry<Integer, TreeSet<Integer>> e : countToSongs.entrySet()) {
            int count = e.getKey();
            for (int songId : e.getValue()) {
                System.out.println(idToTitle.get(songId) + " (" + count + " unique listeners)");
            }
        }
    }

    public void print_top_k(int K) {
        int printed = 0;
        outer:
        for (Map.Entry<Integer, TreeSet<Integer>> e : countToSongs.entrySet()) {
            int count = e.getKey();
            for (int songId : e.getValue()) {
                System.out.println(idToTitle.get(songId) + " (" + count + " unique listeners)");
                if (++printed == K) break outer;
            }
        }
    }

    public List<Integer> last_three_played_songs(int userId) {
        List<Integer> ans = new ArrayList<>(3);
        DLL dll = userList.get(userId);
        if (dll == null) return ans;
        Node cur = dll.front();
        for (int i = 0; i < 3 && cur != null && cur.songId != -1; i++) {
            ans.add(cur.songId);
            cur = cur.next;
        }
        return ans;
    }
}
