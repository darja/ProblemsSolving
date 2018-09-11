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
}
