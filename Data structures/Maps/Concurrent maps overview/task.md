# `ConcurrentHashMap`

Normal `HashMap` is not thread safe.
All operations can make map inconsistent.
Individual operations on `ConcurrentHashMap` are thread safe.
However, individual operations on `ConcurrentHashMap` are not atomic.
For example, this is broken.
Can you tell why?

```java
ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();
if(!map.containsKey("key")) {
    map.put("key", 1);
} else {
    map.put("key", map.get("key") + 1);
}
```

# More materials

* [Why accessing Java HashMap may cause infinite loop in concurrent environment](https://www.pixelstech.net/article/1585457836-Why-Java-HashMap-is-considered-as-thread-unsafe)
* [Java HashMap.get(Object) infinite loop](https://stackoverflow.com/questions/35534906/java-hashmap-getobject-infinite-loop)