package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {
    private CacheManager existCache;
    private CacheManager nonExistCache;
    private BloomFilter bloomFilter;
    private Set<String> files;

    
    public Dictionary(String... fileNames) {
        existCache = new CacheManager(400, new LRU(400));
        nonExistCache = new CacheManager(100, new LFU(100));
        bloomFilter = new BloomFilter(256, "MD5", "SHA-1");
        files = new HashSet<>();
        for (String fileName : fileNames) {
            files.add(fileName);
            populateBloomFilter(fileName);
        }
    }

    
    private void populateBloomFilter(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String word;
            while ((word = reader.readLine()) != null) {
                String[] words = word.split("\\s+");
                for (String w : words) {
                    bloomFilter.add(w);
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to read file: " + fileName + " due to " + e.getMessage());
        }
    }

    
    public boolean query(String word) {
        if (existCache.query(word)) {
            return true;
        }
        if (nonExistCache.query(word)) {
            return false;
        }
        boolean mightExist = bloomFilter.contains(word);
        if (mightExist) {
            try {
                boolean found = IOSearcher.search(word, files.toArray(new String[0]));
                if (found) {
                    existCache.add(word);
                    return true;
                } else {
                    nonExistCache.add(word);
                    return false;
                }
            } catch (Exception e) {
                System.err.println("Error during search: " + e.getMessage());
                nonExistCache.add(word);
                return false;
            }
        } else {
            nonExistCache.add(word);
            return false;
        }
    }

    
    public boolean challenge(String word) {
        return query(word);
    }
}
