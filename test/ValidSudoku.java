import junit.framework.TestCase;

import java.util.HashSet;

public class ValidSudoku extends TestCase {
    public boolean isValidSudoku(char[][] board) {
        HashSet<Character> row = new HashSet<>();
        HashSet<Character> col = new HashSet<>();
        HashSet<Character> square = new HashSet<>();

        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (!addToGroup(row, board[i][j])) {
                    return false;
                }

                if (!addToGroup(col, board[j][i])) {
                    return false;
                }

                int sqi = j / 3 + 3 * (i / 3);
                int sqj = j % 3 + 3 * (i % 3);

                if (!addToGroup(square, board[sqi][sqj])) {
                    return false;
                }

            }
            col.clear();
            row.clear();
            square.clear();
        }

        return true;
    }

    private boolean addToGroup(HashSet<Character> group, char c) {
        if (c != '.') {
            if (group.contains(c)) {
                return false;
            }
            group.add(c);
        }
        return true;
    }

    public void test() {
        testSingle(true,
            "53. .7. ..." +
            "6.. 195 ..." +
            ".98 ... .6." +

            "8.. .6. ..3" +
            "4.. 8.3 ..1" +
            "7.. .2. ..6" +

            ".6. ... 28." +
            "... 419 ..5" +
            "... .8. .79"
            );

        testSingle(true,
            "5.. ... ..." +
            "... ... ..." +
            "... ... ..." +

            "... ... ..." +
            "... ... ..." +
            "... ... ..." +

            "... ... ..." +
            "... ... ..." +
            "... ... ..."
            );

        testSingle(false,
            "53. .7. ..." +
            "6.. 195 ..." +
            ".98 ... .6." +

            "8.. .6. ..3" +
            "4.. 873 ..1" +
            "7.. .2. ..6" +

            ".6. ... 28." +
            "... 419 ..5" +
            "... .8. .79"
            );

        testSingle(false,
            "53. .73 ..." +
            "6.. 195 ..." +
            ".98 ... .6." +

            "8.. .6. ..3" +
            "4.. 8.3 ..1" +
            "7.. .2. ..6" +

            ".6. ... 28." +
            "... 419 ..5" +
            "... .8. .79"
        );

        testSingle(false,
            "53. .7. ..." +
            "6.3 195 ..." +
            ".98 ... .6." +

            "8.. .6. ..3" +
            "4.. 8.3 ..1" +
            "7.. .2. ..6" +

            ".6. ... 28." +
            "... 419 ..5" +
            "... .8. .79"
        );
    }

    private void testSingle(boolean expected, String boardStr) {
        char[][] board = new char[9][9];

        boardStr = boardStr.replaceAll("\\s", "");
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                board[i][j] = boardStr.charAt(i * 9 + j);
            }
        }

        assertEquals(expected, isValidSudoku(board));
    }
}
