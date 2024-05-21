package test;
import java.util.*;

public class LFU implements CacheReplacementPolicy {
    private Map<String, Node> map;  
    private Map<Integer, DoublyLinkedList> freqMap;  
    private int minFreq;  
    private int capacity;  

    public LFU() {
        this(10); 
    }
    
    public LFU(int capacity) {
        this.capacity = capacity;  
        map = new HashMap<>();  
        freqMap = new HashMap<>();  
        minFreq = 0;  
    }

    @Override
    public void add(String word) {
        if(map.containsKey(word)) {  
            Node node = map.get(word);  
            updateNode(node);  
        } else {
            if(map.size() == capacity) {  
                DoublyLinkedList list = freqMap.get(minFreq); 
                map.remove(list.removeLast().word);  
            }

            Node newNode = new Node(word);  
            newNode.freq = 1;  
            minFreq = 1;  
            DoublyLinkedList newList = freqMap.getOrDefault(1, new DoublyLinkedList());  
            newList.add(newNode);  
            freqMap.put(1, newList);  
            map.put(word, newNode);  
        }
    }

    @Override
    public String remove() {
        if(freqMap.containsKey(minFreq)) {  
            Node node = freqMap.get(minFreq).removeLast();  
            if(node != null) {
                map.remove(node.word);  
                return node.word;  
            }
        }
        return null;
    }

    private void updateNode(Node node) {
        int freq = node.freq;  
        DoublyLinkedList list = freqMap.get(freq);  
        list.remove(node);  

        if(freq == minFreq && list.size == 0) {  
            minFreq++;  
        }

        node.freq++;  
        DoublyLinkedList newList = freqMap.getOrDefault(node.freq, new DoublyLinkedList());  // קבלת או יצירת רשימה חדשה לתדירות החדשה.
        newList.add(node);  
        freqMap.put(node.freq, newList);  
    }

    private class Node {
        String word;  
        int freq;  
        Node prev, next;  

        public Node(String word) {
            this.word = word;  
            this.freq = 0;
        }
    }

    private class DoublyLinkedList {
        Node head, tail;  
        int size;  

        public DoublyLinkedList() {
            head = new Node(null);  
            tail = new Node(null);  
            head.next = tail;  
            tail.prev = head;  
            size = 0;  
        }

        void add(Node node) {
            Node headNext = head.next;  
            head.next = node;  
            node.prev = head;  
            node.next = headNext;  
            headNext.prev = node;  
            size++;  
        }

        void remove(Node node) {
            Node nextNode = node.next;  
            Node prevNode = node.prev;  
            nextNode.prev = prevNode;  
            prevNode.next = nextNode;  
            size--;  
        }

        Node removeLast() {
            if(size > 0) {  
                Node node = tail.prev;  
                remove(node);  
                return node;  
            }
            return null;
        }
    }
}
