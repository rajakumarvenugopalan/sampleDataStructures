package basicChecks;

import dataStructures.LRUCache;

public class LRUCacheChecker {

    public static void main(String... args) {
        LRUCache<String> cache = new LRUCache<>();

        cache.add("1");
        cache.add("2");
        cache.add("3");
        cache.add("4");
        cache.add("5");
        cache.add("5");
        cache.add("3");
        cache.add("2");
        cache.add("2");
        cache.add("3");
        cache.add("4");
        cache.add("5");
        cache.add("1");
        cache.add("2");
        cache.add("1");

        System.out.println(cache);
    }
}
