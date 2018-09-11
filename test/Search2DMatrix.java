import junit.framework.TestCase;

public class Search2DMatrix extends TestCase {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int rowFrom = 0;
        int rowTo = matrix.length - 1;
        int row;

        do {
            row = (rowFrom + rowTo) / 2;
            if (matrix[row][cols - 1] == target) {
                return true;
            } else if (matrix[row][cols - 1] < target) {
                if (row == rows - 1) {
                    return false;
                }
                rowFrom = ++row;
            } else {
                rowTo = row;
            }
        } while (rowFrom < rowTo);

        int colFrom = 0;
        int colTo = cols - 1;
        int col;

        do {
            col = (colFrom + colTo) / 2;
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                if (col + 1 > cols - 1) {
                    break;
                }
                colFrom = ++col;
            } else {
                if (col < 1) {
                    break;
                }
                colTo = --col;
            }
        } while (colFrom < colTo);

        return matrix[row][col] == target;
    }

    public boolean searchMatrix_Slow(int[][] matrix, int target) {
        int i = -1;
        int rows = matrix.length;
        while (i < rows - 1 && (matrix[i + 1].length == 0 || target >= matrix[i + 1][0])) {
            ++i;
        }

        if (i < 0) {
            return false;
        }

        int cols = matrix.length > 0 ? matrix[i].length : 0;
        for (int j = 0; j < cols; ++j) {
            if (matrix[i][j] == target) {
                return true;
            }
        }
        return false;
    }

    public void testMatrix1() {
        int[][] matrix = new int[][] {
            {1, 3, 5, 6, 8},
            {10, 11, 19, 26, 44},
            {51, 55, 80, 102, 400}
        };
        assertFalse(searchMatrix(matrix, -1));
        assertTrue(searchMatrix(matrix, 1));
        assertFalse(searchMatrix(matrix, 2));
        assertTrue(searchMatrix(matrix, 3));
        assertFalse(searchMatrix(matrix, 4));
        assertTrue(searchMatrix(matrix, 5));
        assertTrue(searchMatrix(matrix, 10));
        assertTrue(searchMatrix(matrix, 11));
        assertFalse(searchMatrix(matrix, 12));
        assertTrue(searchMatrix(matrix, 44));
        assertTrue(searchMatrix(matrix, 19));
        assertTrue(searchMatrix(matrix, 51));
        assertFalse(searchMatrix(matrix, 52));
        assertFalse(searchMatrix(matrix, 300));
        assertTrue(searchMatrix(matrix, 400));
        assertFalse(searchMatrix(matrix, 500));
    }

    public void testMatrix2() {
        int[][] matrix = new int[][] {
            {1,   3,  5,  7},
            {10, 11, 16, 20},
            {23, 30, 34, 50}
        };

        assertTrue(searchMatrix(matrix, 1));
        assertTrue(searchMatrix(matrix, 3));
        assertTrue(searchMatrix(matrix, 5));
        assertTrue(searchMatrix(matrix, 7));
        assertTrue(searchMatrix(matrix, 10));
        assertTrue(searchMatrix(matrix, 11));
        assertTrue(searchMatrix(matrix, 16));
        assertTrue(searchMatrix(matrix, 20));
        assertTrue(searchMatrix(matrix, 23));
        assertTrue(searchMatrix(matrix, 30));
        assertTrue(searchMatrix(matrix, 34));
        assertTrue(searchMatrix(matrix, 50));

        assertFalse(searchMatrix(matrix, 2));
        assertFalse(searchMatrix(matrix, 12));
        assertFalse(searchMatrix(matrix, 13));
        assertFalse(searchMatrix(matrix, 21));
    }

    public void testRow() {
        int[][] matrix = new int[][] {
            {1, 3}
        };
        assertFalse(searchMatrix(matrix, 0));
        assertTrue(searchMatrix(matrix, 1));
        assertFalse(searchMatrix(matrix, 2));
        assertTrue(searchMatrix(matrix, 3));
        assertFalse(searchMatrix(matrix, 20));
    }

    public void testCol() {
        int[][] matrix = new int[][] {
            {1},
            {3}
        };
        assertFalse(searchMatrix(matrix, 0));
        assertTrue(searchMatrix(matrix, 1));
        assertFalse(searchMatrix(matrix, 2));
        assertTrue(searchMatrix(matrix, 3));
        assertFalse(searchMatrix(matrix, 20));
    }

    public void testEmpty() {
        int[][] matrix = new int[0][0];
        assertFalse(searchMatrix(matrix, 0));
        assertFalse(searchMatrix(matrix, 1));
    }

    public void testEmptyRow() {
        int[][] matrix = new int[1][0];
        assertFalse(searchMatrix(matrix, 0));
        assertFalse(searchMatrix(matrix, 1));
    }
}
