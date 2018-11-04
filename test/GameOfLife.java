import util.MatrixTestCase;

public class GameOfLife extends MatrixTestCase {
    private void gameOfLife(int[][] board) {
        if (board == null) return;

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                int neighboursCount = neighboursCount(board, i, j);
                int isAlive = board[i][j];

                if (isAlive == 0) {
                    if (neighboursCount == 3) {
                        isAlive = 1;
                    }
                } else {
                    if (neighboursCount < 2 || neighboursCount > 3) {
                        isAlive = 0;
                    }
                }

                board[i][j] = isAlive * 10 + board[i][j];
            }
        }

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                board[i][j] = board[i][j] / 10;
            }
        }
    }

    private int neighboursCount(int[][] board, int i, int j) {
        return cellAt(board, i - 1, j - 1) + cellAt(board, i - 1, j) + cellAt(board, i - 1, j + 1) +
            cellAt(board, i, j - 1) + cellAt(board, i, j + 1) +
            cellAt(board, i + 1, j - 1) + cellAt(board, i + 1, j) + cellAt(board, i + 1, j + 1);
    }

    private int cellAt(int[][] board, int i, int j) {
        if (i >= 0 && j >=0 && i < board.length && j < board[0].length) {
            return board[i][j] % 10;
        }
        return 0;
    }

    private void printMatrix(int[][] board) {
        if (board == null) {
            System.out.println("null");
            return;
        }

        for (int[] row : board) {
            for (int item : row) {
                System.out.print(item);
            }
            System.out.println();
        }
    }


    private void testOne(int[][] board, int[][] expected) {
        System.out.println("BEFORE");
        printMatrix(board);
        gameOfLife(board);
        System.out.println("AFTER");
        printMatrix(board);
        assertMatricesEqualOrdered(expected, board);
    }

    public void testNull() {
        testOne(null, null);
    }

    public void testOneAlive() {
        int[][] board = new int[][] {
            new int[] {1}
        };
        int[][] expected = new int[][] {
            new int[] {0}
        };
        testOne(board, expected);
    }

    public void testOneDead() {
        int[][] board = new int[][] {
            new int[] {0}
        };
        int[][] expected = new int[][] {
            new int[] {0}
        };
        testOne(board, expected);
    }

    public void testRow() {
        int[][] board = new int[][] {
            new int[] {0, 1, 1, 1}
        };
        int[][] expected = new int[][] {
            new int[] {0, 0, 1, 0}
        };
        testOne(board, expected);
    }

    public void testCol() {
        int[][] board = new int[][] {
            new int[] {0},
            new int[] {1},
            new int[] {1},
            new int[] {1}
        };
        int[][] expected = new int[][] {
            new int[] {0},
            new int[] {0},
            new int[] {1},
            new int[] {0}
        };
        testOne(board, expected);
    }

    public void testAllDead() {
        int[][] board = new int[][] {
            new int[] {0, 0, 0},
            new int[] {0, 0, 0},
            new int[] {0, 0, 0}
        };
        int[][] expected = new int[][] {
            new int[] {0, 0, 0},
            new int[] {0, 0, 0},
            new int[] {0, 0, 0}
        };
        testOne(board, expected);
    }

    public void testAlone() {
        int[][] board = new int[][] {
            new int[] {0, 0, 0},
            new int[] {0, 1, 0},
            new int[] {0, 0, 0}
        };
        int[][] expected = new int[][] {
            new int[] {0, 0, 0},
            new int[] {0, 0, 0},
            new int[] {0, 0, 0}
        };
        testOne(board, expected);
    }

    public void testAllAlive() {
        int[][] board = new int[][] {
            new int[] {1, 1, 1},
            new int[] {1, 1, 1},
            new int[] {1, 1, 1}
        };
        int[][] expected = new int[][] {
            new int[] {1, 0, 1},
            new int[] {0, 0, 0},
            new int[] {1, 0, 1}
        };
        testOne(board, expected);
    }

    public void testBigger1() {
        int[][] board = new int[][] {
            new int[] {0, 1, 0, 1},
            new int[] {1, 1, 0, 0},
            new int[] {1, 0, 0, 1},
            new int[] {1, 1, 0, 1}
        };
        int[][] expected = new int[][] {
            new int[] {1, 1, 1, 0},
            new int[] {1, 1, 0, 0},
            new int[] {0, 0, 0, 0},
            new int[] {1, 1, 1, 0}
        };
        testOne(board, expected);
    }

    public void testBigger2() {
        int[][] board = new int[][] {
            new int[] {1, 1, 1},
            new int[] {1, 1, 1},
            new int[] {0, 0, 0},
        };
        int[][] expected = new int[][] {
            new int[] {1, 0, 1},
            new int[] {1, 0, 1},
            new int[] {0, 1, 0},
        };
        testOne(board, expected);
    }
}
