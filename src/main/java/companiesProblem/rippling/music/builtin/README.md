# Music Player Analytics - Built-in DLL Version

## Intuition

Ye same music analytics problem ka simpler version hai jisme custom DLL nahi likha.

Java ka `LinkedHashMap` internally doubly linked list maintain karta hai. Agar `accessOrder=true` use karein, to jis song ko dubara access/play karte hain, wo automatically most recent position par chala jata hai.

Isliye user history ke liye:

- Key: `songId`
- Value: dummy `Boolean`
- Order: oldest to newest internally
- Reverse iteration se last 3 recent songs mil jate hain

Ranking ke liye ab bhi 2 TreeSet use hote hain:

- `uniqueListenerRanking`: unique listeners ke liye.
- `totalPlayRanking`: total plays ke top K ke liye.

## Classes

- `MusicPlayerAnalyticsBuiltInDll`: main API class.
- `Song`: song details, unique listeners, total play count.
- `UserHistory`: `LinkedHashMap` based recent unique song history.

## Functions

- `addSong(String songTitle)`: song add karta hai and id return karta hai. Time: O(log N).
- `playSong(int songId, int userId)`: total plays, unique listeners, and user recency update karta hai. Time: O(log N).
- `print_summary_for_unique_listeners()`: unique listeners ke basis par all songs print karta hai. Time: O(N).
- `print_summary_for_top_k(int k)`: total plays ke basis par top K songs print karta hai. Time: O(K).
- `last_three_played_songs(int userId)`: last 3 unique songs return karta hai. Time: O(3), practically O(1).
- `lastThreePlayedSongs(int userId)`: camelCase wrapper. Time: O(3), practically O(1).

## Complexity

- `addSong`: O(log N)
- `playSong`: O(log N)
- `print_summary_for_unique_listeners`: O(N)
- `print_summary_for_top_k`: O(K)
- `last_three_played_songs`: O(3), practically O(1)
