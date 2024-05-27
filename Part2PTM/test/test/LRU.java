package test;

import java.util.HashMap;
import java.util.Map;

public class LRU implements CacheReplacementPolicy {
    private Map<String, Node> cacheMap;
    private Node head, tail;
    private final int capacity;

   
    public LRU() {
        this(10); 
    }

  
    public LRU(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();
    }

    @Override
    public void add(String word) {
        if (cacheMap.containsKey(word)) {
            Node node = cacheMap.get(word);
            remove(node);
            setHead(node);
        } else {
            Node newNode = new Node(word);
            if (cacheMap.size() == capacity) {
                cacheMap.remove(tail.word);
                remove(tail);
            }
            setHead(newNode);
            cacheMap.put(word, newNode);
        }
    }

    @Override
    public String remove() {
        if (tail != null) {
            String val = tail.word;
            remove(tail);
            cacheMap.remove(val);
            return val;
        }
        return null; 
    }

    private void setHead(Node node) {
        node.next = head;
        node.prev = null;
        if (head != null) {
            head.prev = node;
        }
        head = node;
        if (tail == null) {
            tail = head;
        }
    }

    private void remove(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
    }

    private class Node {
        String word;
        Node prev, next;

        Node(String word) {
            this.word = word;
        }
    }
}
