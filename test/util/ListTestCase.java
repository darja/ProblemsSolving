package util;

import junit.framework.TestCase;

import java.util.List;

public class ListTestCase extends TestCase {
    protected void assertListsEqual(String[] l1, List<String> l2) {
        if (l1 == null) {
            assertNull(l2);
        }

        if (l2 == null) {
            assertNull(l1);
        }

        assertEquals("List length", l1.length, l2.size());

        for (String item : l1) {
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
}
