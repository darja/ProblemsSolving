package util;

import junit.framework.TestCase;

import java.util.List;

public class ListTestCase extends TestCase {
    protected <T> void assertListsEqual(T[] l1, List<T> l2) {
        if (l1 == null) {
            assertNull(l2);
        }
        assertNotNull(l2);

        assertEquals("List length", l1.length, l2.size());

        for (T item : l1) {
            assertTrue(l2.contains(item));
        }
    }

    protected void assertListsEqual(int[] l1, List<Integer> l2) {
        if (l1 == null) {
            assertNull(l2);
        }

        if (l2 == null) {
            assertNull(l1);
        }

        assertEquals("List length", l1.length, l2.size());

        for (int item : l1) {
            assertTrue(l2.contains(item));
        }
    }

    protected <T> void assertListsEqualOrdered(T[] l1, List<T> l2) {
        if (l1 == null) {
            assertNull(l2);
        }
        assertNotNull(l2);

        assertEquals("List length", l1.length, l2.size());

        for (int i = 0; i < l1.length; ++i) {
            assertEquals(l1[i], l2.get(i));
        }
    }

    protected <T> boolean areListsEqualOrdered(T[] l1, List<T> l2) {
        if (l1 == null) return l2 == null;
        if (l2 == null) return false;
        if (l1.length != l2.size()) return false;

        for (int i = 0; i < l1.length; ++i) {
            if (l1[i] != l2.get(i)) return false;
        }
        return true;
    }
}
