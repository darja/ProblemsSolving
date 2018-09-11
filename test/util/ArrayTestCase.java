package util;

import junit.framework.TestCase;

public class ArrayTestCase extends TestCase {
    protected void assertArraysEqual(int[] l1, int[] l2) {
        if (l1 == null) {
            assertNull(l2);
        }

        if (l2 == null) {
            assertNull(l1);
        }

        assertEquals(l1.length, l2.length);

        for (int i = 0; i < l1.length; ++i) {
            assertEquals(l1[i], l2[i]);
        }
    }
}
