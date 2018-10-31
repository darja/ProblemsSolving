import javafx.util.Pair;
import junit.framework.TestCase;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

// 00 01 02 | 03 04 05 | 06 07 08
// 10 11 12 | 13 14 15 | 16 17 18
// 20 21 22 | 23 24 25 | 26 27 28
// ------------------------------
// 30 31 32 | 33 34 35 | 36 37 38
// 40 41 42 | 43 44 45 | 46 47 48
// 50 51 52 | 53 54 55 | 56 57 58
// ------------------------------
// 60 61 62 | 63 64 65 | 66 67 68
// 70 71 72 | 73 74 75 | 76 77 78
// 80 81 82 | 83 84 85 | 86 87 88

public class SudokuSolver extends TestCase {
    public void solveSudoku(char[][] board) {
        int numsLeft = 0;

        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    numsLeft++;
                }
            }
        }

        solveIter(board, numsLeft, new LinkedList<Pair<Integer, Integer>>());
    }

    private boolean solveIter(char[][] board, int numsLeft, List<Pair<Integer, Integer>> diff) {
        boolean isStuck = false;
        while (numsLeft != 0 && !isStuck) {
            isStuck = true;
            for (int i = 0; i < 9; ++i) {
                for (int j = 0; j < 9; ++j) {
                    if (board[i][j] == '.') {
                        Character guessed = guessNumber(board, i, j);
                        if (guessed != null) {
                            if (guessed == '0') {
                                System.out.println(boardToString(board));
                                System.out.printf("Conflict at [%s][%s]\n", i, j);

                                return false;
                            }
                            isStuck = false;
                            numsLeft--;
                            board[i][j] = guessed;

                            if (!diff.isEmpty()) {
                                diff.add(new Pair<>(i, j));
                            }
                        }
                    }
                }
            }
        }

        System.out.println("*** Iteration over***");
        System.out.println(boardToString(board));

        if (numsLeft == 0) {
            return true;
        }

        int emptyRow = 0;
        int emptyCol = 0;
        for (int i = 0; i < 9; ++i) {
            boolean found = false;
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    emptyRow = i;
                    emptyCol = j;
                    found = true;
                    break;
                }
            }
            if (found) break;
        }

        HashSet<Character> candidates = findCandidates(board, emptyRow, emptyCol);
        LinkedList<Pair<Integer, Integer>> newDiff = new LinkedList<>();
        for (Character candidate : candidates) {
            newDiff.clear();
            newDiff.add(new Pair<>(emptyRow, emptyCol));

            board[emptyRow][emptyCol] = candidate;
            System.out.printf("Candidate [%s][%s]: %s\n", emptyRow, emptyCol, candidate);
            if (solveIter(board, numsLeft - 1, newDiff)) {
                return true;
            } else {
                for (Pair<Integer, Integer> item: newDiff) {
                    board[item.getKey()][item.getValue()] = '.';
                }
                System.out.printf("FAILED Candidate [%s][%s]: %s\n", emptyRow, emptyCol, candidate);
                System.out.println(boardToString(board));
            }
        }

        return false;
    }

    private HashSet<Character> findCandidates(char[][] board, int row, int col) {
        HashSet<Character> options = new HashSet<>();
        for (char c = '1'; c <= '9'; ++c) {
            options.add(c);
        }

        for (int i = 0; i < 9; ++i) {
            options.remove(board[i][col]);
            options.remove(board[row][i]);
        }

        int sqrow = 3 * (row / 3);
        int sqcol = 3 * (col / 3);
        for (int i = sqrow; i < sqrow + 3; ++i) {
            for (int j = sqcol; j < sqcol + 3; ++j) {
                options.remove(board[i][j]);
            }
        }
        return options;
    }

    private Character guessNumber(char[][] board, int row, int col) {
        HashSet<Character> options = findCandidates(board, row, col);

        if (options.isEmpty()) {
            return '0';
        }

        if (options.size() == 1) {
            return options.iterator().next();
        }

        return null;
    }

    private void testSingle(String[] input) {
        char[][] board = new char[9][9];
        for (int i = 0; i < 9; ++i)
            for (int j = 0; j < 9; ++j) {
                board[i][j] = input[i].charAt(j);
            }
        solveSudoku(board);

        System.out.println(boardToString(board));

        assertSolution(board);
    }

    private String boardToString(char[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                sb.append(board[i][j]);
                sb.append(" ");
                if (j % 3 == 2) {
                    sb.append("| ");
                }
            }
            sb.append("\n");
            if (i % 3 == 2) {
                sb.append("----------------------\n");
            }
        }
        return sb.toString();
    }

    public void test1() {
        testSingle(new String[] {
            "53..7....",
            "6..195...",
            ".98....6.",
            "8...6...3",
            "4..8.3..1",
            "7...2...6",
            ".6....28.",
            "...419..5",
            "....8..79"
        });
    }

    public void test2() {
        testSingle(new String[] {
            "..9748...",
            "7........",
            ".2.1.9...",
            "..7...24.",
            ".64.1.59.",
            ".98...3..",
            "...8.3.2.",
            "........6",
            "...2759.."
        });
    }

    private void assertSolution(char[][] board) {
        // check if all filled
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                assertNotSame(String.format("Item not solved: [%s, %s]", i, j), '.', board[i][j]);
            }
        }

        // check solution
        HashSet<Character> row = new HashSet<>();
        HashSet<Character> col = new HashSet<>();
        HashSet<Character> square = new HashSet<>();

        for (int i = 0; i < 9; ++i) {
            col.clear();
            row.clear();
            square.clear();
            for (int j = 0; j < 9; ++j) {
                assertFalse("Duplicate item at row " + i, row.contains(board[i][j]));
                row.add(board[i][j]);

                assertFalse("Duplicate item at col " + i, col.contains(board[j][i]));
                col.add(board[j][i]);

                int sqi = j / 3 + 3 * (i / 3);
                int sqj = j % 3 + 3 * (i % 3);
                assertFalse("Duplicate item at square " + i, square.contains(board[sqi][sqj]));
                square.add(board[sqi][sqj]);
            }
        }
    }
}
