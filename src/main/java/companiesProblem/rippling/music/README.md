# Music Player Analytics

## Intuition

Is problem me hume 3 cheeze fast chahiye:

1. Song id se song ko jaldi find karna.
2. Unique listeners ke basis par summary print karna.
3. Har user ke last 3 unique played songs nikalna.

Isliye solution me:

- `songMap` rakha hai: `songId -> Song`, taaki `playSong()` me song O(1) me mil jaye.
- `uniqueListenerRanking` rakha hai: unique listeners ke basis par sorted songs.
- `totalPlayRanking` rakha hai: total plays ke basis par top K songs.
- `userMap` rakha hai: har user ki recent unique song history.
- User history ke liye custom DLL + map use kiya hai, LRU style. Same song dubara play hua to old node hata ke front me daal dete hain.

Important interview point:

TreeSet me jis field par sorting ho rahi hai, us field ko update karne se pehle object ko TreeSet se remove karo, update karo, fir reinsert karo.

## Classes

- `MusicPlayerAnalytics`: main API class.
- `Song`: song id, title, unique listeners, total play count store karta hai.
- `UserHistory`: ek user ke recent unique songs maintain karta hai.
- `Node`: custom doubly linked list node.
- `Main`: alternate bucket-style implementation demo.

## Functions

- `addSong(String songTitle)`: new song add karta hai, id auto-generate hoti hai. Time: O(log N).
- `playSong(int songId, int userId)`: song play record karta hai, total play count update karta hai, unique listener once count karta hai. Time: O(log N).
- `print_summary_for_unique_listeners()`: all songs unique listeners ke descending order me print karta hai. Time: O(N).
- `print_summary_for_top_k(int k)`: total plays ke basis par top K songs print karta hai. Time: O(K).
- `last_three_played_songs(int userId)`: user ke last 3 unique songs return karta hai. Time: O(3), practically O(1).

## Complexity

- `addSong`: O(log N)
- `playSong`: O(log N)
- `print_summary_for_unique_listeners`: O(N)
- `print_summary_for_top_k`: O(K)
- `last_three_played_songs`: O(3), practically O(1)
