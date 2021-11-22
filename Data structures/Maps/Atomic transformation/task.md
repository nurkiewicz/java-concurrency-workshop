There's a `scores` `Map<String, Integer>` that holds the number of points per each player.
Implement two methods:

* `createScoresMap()` that creates a new `Map scores` instance - it must be thread-safe!
* `applyScore(String player, int points)` that adds a new player with the given number of points to the `Map`. `points` can be negative

Keep in mind that when previously unseen `player` appears, it should be added to the map.
If it was previously seen, the number of points should be incremented/decremented.

<div class="hint">

Keep in mind that even if `get()` and `put()` (or even `putIfAbsent()`) methods are individually thread-safe, multiple calls are not "transactional".

</div>