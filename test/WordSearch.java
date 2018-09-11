import junit.framework.TestCase;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class WordSearch extends TestCase {
    public void testEmpty() {
        char[][] board = new char[0][0];
        assertTrue(exist(board, ""));
        assertFalse(exist(board, "ab"));
    }

    public void testNonEmpty() {
        char[][] board = new char[][] {
            { 'A', 'B', 'C', 'D' },
            { 'E', 'F', 'K', 'R' },
            { 'E', 'D', 'P', 'K' },
        };
        assertTrue(exist(board, "ABF"));
        assertTrue(exist(board, "CKRK"));
        assertTrue(exist(board, "FEE"));
        assertTrue(exist(board, "DEEA"));
        assertTrue(exist(board, "KRDC"));
        assertTrue(exist(board, "KR"));
        assertTrue(exist(board, "RKFBA"));
        assertTrue(exist(board, "DCBAEE"));
        assertTrue(exist(board, "KPKCD"));

        assertFalse(exist(board, "AA"));
        assertFalse(exist(board, "ABD"));
        assertFalse(exist(board, "LN"));
        assertFalse(exist(board, "EEA0"));
    }

    public void testRow() {
        char[][] board = new char[][]{
            {'A', 'B', 'C', 'D'}
        };

        assertTrue(exist(board, "AB"));
        assertTrue(exist(board, "CBA"));
        assertTrue(exist(board, "DCBA"));

        assertFalse(exist(board, "AA"));
        assertFalse(exist(board, "LLL"));
        assertFalse(exist(board, "ABP"));
    }

    public void testCol() {
        char[][] board = new char[][]{
            {'A'}, {'B'}, {'C'}, {'D'}
        };

        assertTrue(exist(board, "AB"));
        assertTrue(exist(board, "CBA"));
        assertTrue(exist(board, "DCBA"));

        assertFalse(exist(board, "AA"));
        assertFalse(exist(board, "LLL"));
        assertFalse(exist(board, "ABP"));
    }

    public void testCell() {
        char[][] board = new char[][]{
            {'A'}
        };

        assertTrue(exist(board, ""));
        assertTrue(exist(board, ""));
        assertTrue(exist(board, "A"));
        assertFalse(exist(board, "AA"));
        assertFalse(exist(board, "AB"));
        assertFalse(exist(board, "B"));
    }

    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        if (rows == 0) {
            return word.length() == 0;
        }

        int cols = board[0].length;
        if (cols == 0) {
            return word.length() == 0;
        }

        if (cols == 1 && rows == 1) {
            return word.length() == 1 && word.charAt(0) == board[0][0] ||
                word.length() == 0;
        }

        HashSet<Cell> pathCells = new HashSet<>();

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (existBack(board, word, 0, pathCells, i, j, rows, cols)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean existBack(char[][] board, String word, int charIndex, HashSet<Cell> pathCells,
                              int i, int j, int rows, int cols) {
        if (charIndex == word.length()) {
            return true;
        }

        Character c = word.charAt(charIndex);

        List<Cell> cells = getNeighbours(i, j);
        for (Cell cell: cells) {
            if (cell.isValid(rows, cols) && !pathCells.contains(cell) && c == board[cell.i][cell.j]) {
                pathCells.add(cell);
                if (existBack(board, word, charIndex + 1, pathCells, cell.i, cell.j, rows, cols)) {
                    return true;
                }
                pathCells.remove(cell);
            }
        }

        return false;
    }

    private List<Cell> getNeighbours(final int i, final int j) {
        return new LinkedList<Cell>() {{
            add(new Cell(i    , j - 1));
            add(new Cell(i - 1, j));
            add(new Cell(i + 1, j));
            add(new Cell(i    , j + 1));
        }};
    }

    private class Cell {
        final int i;
        final int j;

        private Cell(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public boolean isValid(int rows, int cols) {
            return i >= 0 && j >= 0 && i < rows && j < cols;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Cell)) {
                return false;
            }
            Cell cell = (Cell) obj;
            return cell.i == i && cell.j == j;
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + i;
            result = 31 * result + j;
            return result;
        }
    }
}
