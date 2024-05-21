package test;

import java.util.Arrays;
import java.util.BitSet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

public class BloomFilter {
    private BitSet bitSet;
    private int bitSetSize;
    private MessageDigest[] hashFunctions;

    
    public BloomFilter(int bitSetSize, String... hashAlgorithms) {
        this.bitSetSize = bitSetSize;
        this.bitSet = new BitSet(bitSetSize);
        this.hashFunctions = new MessageDigest[hashAlgorithms.length];

        try {
            for (int i = 0; i < hashAlgorithms.length; i++) {
                this.hashFunctions[i] = MessageDigest.getInstance(hashAlgorithms[i]);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(String element) {
        byte[] bytes = element.getBytes();
        for (MessageDigest hashFunction : hashFunctions) {
            
            byte[] digest = hashFunction.digest(bytes);
           
            BigInteger hashIndex = new BigInteger(digest); 
            int index=Math.abs(hashIndex.intValue())%this.bitSetSize;
            
            
            bitSet.set(index, true);
           
        }
    }

   
    public boolean contains(String element) {
        byte[] bytes = element.getBytes();
        for (MessageDigest hashFunction : hashFunctions) {
           
            byte[] digest = hashFunction.digest(bytes);
            BigInteger hashIndex = new BigInteger(digest);
            int index=Math.abs(hashIndex.intValue())%this.bitSetSize;
            
            if (!bitSet.get(index)) {
                return false; 
            }
        }
        return true; 
    } 
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < bitSet.length(); i++) {
            result.append(bitSet.get(i) ? '1' : '0');
        }
        return result.toString();
    }

    
}