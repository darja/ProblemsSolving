package util;

import junit.framework.TestCase;

import java.util.List;

public class NestedListTestCase extends TestCase {
    protected void assertEquals(List<List<Integer>> actual, int[][] expected) {
        assertEquals("Nested list size", expected.length, actual.size());

        for (int[] ex : expected) {
            boolean found = true;

            for (List<Integer> subset : actual) {
                if (subset.size() != ex.length) {
                    continue;
                }
                found = true;
                for (Integer exItem : ex) {
                    if (!subset.contains(exItem)) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
            assertTrue(found);
        }

    }
}
