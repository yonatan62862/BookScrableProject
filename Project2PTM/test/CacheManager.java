package test;

import java.util.HashSet;
import java.util.Set;

public class CacheManager {
    private CacheReplacementPolicy replacementPolicy;
    private Set<String> cache;
    private int capacity;

    public CacheManager(int capacity, CacheReplacementPolicy crp) {
        this.capacity = capacity;
        this.replacementPolicy = crp;
        this.cache = new HashSet<>();
    }

    public void add(String word) {
        if (cache.contains(word)) {
            return; 
        }
        if (cache.size() >= capacity) {
            String toRemove = replacementPolicy.remove(); 
            cache.remove(toRemove); 
        }
        cache.add(word); 
        replacementPolicy.add(word); 
    }

   
    public boolean query(String word) {
        return cache.contains(word); 
    }
}
