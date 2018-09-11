import junit.framework.TestCase;

public class UniqueBinarySearchTrees extends TestCase {
    public int numTrees(int n) {
        int[][] trees = new int[n][n];

        if (n == 1) return 1;
        if (n == 2) return 2;

        trees[0][0] = 1;
        trees[1][0] = 1;
        trees[1][1] = 1;

        int treesCount = 2;
        int rowSum;

        for (int i = 2; i < n; ++i) {
            rowSum = treesCount;
            treesCount = 0;

            for (int j = 0; j < i + 1; ++j) {
                if (j >= 2) {
                    rowSum -= trees[i - 1][j - 2];
                }

                trees[i][j] = rowSum;
                treesCount += rowSum;
            }
        }

        return treesCount;
    }

    public void test() {
        assertEquals("1", 1, numTrees(1));
        assertEquals("2", 2, numTrees(2));
        assertEquals("3", 5, numTrees(3));
        assertEquals("4", 14, numTrees(4));
        assertEquals("5", 42, numTrees(5));
    }
}
