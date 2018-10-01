package design;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache {
    private int capacity;

    private Map<Integer, Integer> content = new HashMap<>();
    private LinkedList<Integer> keysUsage = new LinkedList<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (content.containsKey(key)) {
            int value = content.get(key);
            keysUsage.removeFirstOccurrence(key);
            keysUsage.addLast(key);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (!content.containsKey(key)) {
            if (content.size() == capacity) {
                int keyToRemove = keysUsage.removeFirst();
                content.remove(keyToRemove);
            }
        } else {
            keysUsage.removeFirstOccurrence(key);
        }
        content.put(key, value);
        keysUsage.addLast(key);
    }
}
