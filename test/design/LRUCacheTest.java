package design;

import junit.framework.TestCase;

public class LRUCacheTest extends TestCase {
    public void testOneElement() {
        LRUCache cache = new LRUCache(1);
        cache.put(1, 2);
        assertEquals(2, cache.get(1));

        cache.put(2, 3);

        assertEquals(-1, cache.get(1));
        assertEquals(3, cache.get(2));
        assertEquals(-1, cache.get(-1));
    }

    public void testTwoElements() {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(2, cache.get(2));
        assertEquals(1, cache.get(1));

        cache.put(3, 3); // removes 2
        assertEquals(-1, cache.get(2));
        assertEquals(3, cache.get(3));
        assertEquals(1, cache.get(1));

        cache.put(4, 4);    // removes 3
        assertEquals(4, cache.get(4));
        assertEquals(1, cache.get(1));
        assertEquals(-1, cache.get(3));
        assertEquals(-1, cache.get(2));
        assertEquals(-1, cache.get(20));
    }

    public void testTwoElements2() {
        LRUCache cache = new LRUCache(2);
        assertEquals(-1, cache.get(2));
        cache.put(2, 6);
        assertEquals(-1, cache.get(1));
        cache.put(1, 5);
        cache.put(1, 2);
        assertEquals(2, cache.get(1));
        assertEquals(6, cache.get(2));
    }

    public void testTwoElements3() {
        LRUCache cache = new LRUCache(2);
        cache.put(2, 1);
        cache.put(1, 1);
        cache.put(2, 3);
        cache.put(4, 1);
        assertEquals(-1, cache.get(1));
        assertEquals(3, cache.get(2));
    }
}
