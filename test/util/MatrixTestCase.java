package util;

import junit.framework.TestCase;

import java.util.List;

public class MatrixTestCase extends TestCase {
    protected void assertMatricesEquals(int[][] expected, List<List<Integer>> result) {
        assertEquals(expected.length, result.size());

        for (int[] expectedComb : expected) {
            boolean isCombFound = true;
            for (List<Integer> comb : result) {
                isCombFound = true;
                for (int i = 0; i < expectedComb.length; ++i) {
                    if (!comb.get(i).equals(expectedComb[i])) {
                        isCombFound = false;
                        break;
                    }
                }

                if (isCombFound) {
                    break;
                }
            }
            if (!isCombFound) {
                StringBuilder sb = new StringBuilder();
                sb.append("Combination not found: [");
                for (int i : expectedComb) {
                    sb.append(i);
                    sb.append(", ");
                }
                sb.delete(sb.length() - 2, sb.length());
                sb.append("]");
                fail(sb.toString());
            }
        }
    }

    protected void assertMatricesEqualOrdered(int[][] expected, int[][] actual) {
        if (expected == null) {
            assertNull("Null expected", actual);
            return;
        }

        assertEquals("Rows count", expected.length, actual.length);

        if (expected.length == 0) {
            return; // empty matrices
        }

        assertEquals("Cols count", expected[0].length, actual[0].length);

        for (int i = 0; i < expected.length; ++i) {
            for (int j = 0; j < expected[i].length; ++j) {
                assertEquals(String.format("Item [%s][%s]", i, j), expected[i][j], actual[i][j]);
            }
        }
    }
}
