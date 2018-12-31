package matrix;

import util.MatrixTestCase;

/**
 * @see <a href="https://leetcode.com/problems/image-smoother/">Problem Description</a>
 */
public class ImageSmoother extends MatrixTestCase {
    public int[][] imageSmoother(int[][] M) {
        int rows = M.length;
        if (rows == 0) return M;
        int cols = M[0].length;

        int[][] smooth = new int[rows][cols];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                smooth[i][j] = average(M, i, j);
            }
        }

        return smooth;
    }

    private int average(int[][] src, int i, int j) {
        int[] acc = new int[2];

        countNeighbour(acc, src, i - 1, j - 1);
        countNeighbour(acc, src, i - 1, j);
        countNeighbour(acc, src, i - 1, j + 1);

        countNeighbour(acc, src, i, j - 1);
        countNeighbour(acc, src, i, j);
        countNeighbour(acc, src, i, j + 1);

        countNeighbour(acc, src, i + 1, j - 1);
        countNeighbour(acc, src, i + 1, j);
        countNeighbour(acc, src, i + 1, j + 1);

        return acc[0] / acc[1];
    }

    private void countNeighbour(int[] acc, int[][] src, int i, int j) {
        if (i >= 0 && i < src.length && j >= 0 && j < src[0].length) {
            acc[0] += src[i][j];
            acc[1]++;
        }
    }

    private void testSingle(int[][] src, int[][] expected) {
        assertMatricesEqualOrdered(expected, imageSmoother(src));
    }

    public void test1() {
        int[][] src = new int[][] {
            new int[] {1, 1, 1},
            new int[] {1, 1, 1},
            new int[] {1, 1, 1}
        };

        testSingle(src, src);
    }

    public void test2() {
        int[][] src = new int[][] {
            new int[] {1, 1, 1},
            new int[] {1, 0, 1},
            new int[] {1, 1, 1}
        };
        int[][] expected = new int[][] {
            new int[] {0, 0, 0},
            new int[] {0, 0, 0},
            new int[] {0, 0, 0}
        };

        testSingle(src, expected);
    }

    public void test3() {
        int[][] src = new int[][] {
            new int[] {1, 1, 1},
            new int[] {1, 2, 1},
            new int[] {1, 1, 1}
        };
        int[][] expected = new int[][] {
            new int[] {1, 1, 1},
            new int[] {1, 1, 1},
            new int[] {1, 1, 1}
        };

        testSingle(src, expected);
    }
    public void test4() {
        int[][] src = new int[][] {
            new int[] {1, 3, 1},
            new int[] {2, 2, 1},
            new int[] {1, 1, 1}
        };
        int[][] expected = new int[][] {
            new int[] {2, 1, 1},
            new int[] {1, 1, 1},
            new int[] {1, 1, 1}
        };

        testSingle(src, expected);
    }
}
