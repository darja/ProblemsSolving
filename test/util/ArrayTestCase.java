package util;

import junit.framework.TestCase;

import java.util.Arrays;

public class ArrayTestCase extends TestCase {
    protected void assertArraysEqual(int[] l1, int[] l2) {
        assertArraySize(l1, l2);

        for (int i = 0; i < l1.length; ++i) {
            assertEquals(l1[i], l2[i]);
        }
    }

    protected void assertArraysEqualUnordered(int[] l1, int[] l2) {
        assertArraySize(l1, l2);

        Arrays.sort(l1);
        Arrays.sort(l2);

        for (int i = 0; i < l1.length; ++i) {
            assertEquals(l1[i], l2[i]);
        }
    }

    private void assertArraySize(int[] l1, int[] l2) {
        if (l1 == null) {
            assertNull(l2);
        }

        if (l2 == null) {
            assertNull(l1);
        }

        assertEquals("Array size", l1.length, l2.length);
    }
}
