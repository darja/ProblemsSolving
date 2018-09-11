import junit.framework.TestCase;

public class WildcardMatching extends TestCase {
    public boolean isMatch(String input, String pattern) {
        int iLen = input.length();
        int pLen = pattern.length();

        boolean[][] match = new boolean[pLen + 1][iLen + 1];

        match[0][0] = true;
        for (int i = 0; i < pLen; ++i) {
            if (pattern.charAt(i) == '*') {
                match[i+1][0] = true;
            } else {
                break;
            }
        }

        for (int i = 0; i < pLen; ++i) {
            char p = pattern.charAt(i);

            if (iLen == 0 && p != '*') {
                return false;
            }

            for (int j = 0; j < iLen; ++j) {
                if (p == '*') {
                    match[i + 1][j + 1] = match[i][j + 1] || match[i + 1][j];
                } else {
                    match[i + 1][j + 1] = j >= 0 && match[i][j] && (p == input.charAt(j) || p == '?');
                }
            }
        }

        return match[pLen][iLen];
    }

    public void test() {
        testSingle(true, "abc", "*c*");
        testSingle(true, "a", "*?*");
        testSingle(true, "a", "***");
        testSingle(true, "", "***");
        testSingle(true, "abc", "***");
        testSingle(true, "abc", "**?");
        testSingle(true, "abc", "??*?");
        testSingle(true, "", "*");
        testSingle(true, "", "**");
        testSingle(true, "", "");
        testSingle(false, "", "?");
        testSingle(false, "", "a");
        testSingle(false, "", "ab*");

        testSingle(true, "ab", "a?");
        testSingle(true, "abcdef", "a*");
        testSingle(true, "ab", "ab");
        testSingle(true, "ab", "ab*");
        testSingle(true, "ab", "ab**");
        testSingle(false, "abc", "ab");
        testSingle(false, "abbb", "*c");
        testSingle(true, "abbb", "*b");
        testSingle(false, "a", "*b");
        testSingle(true, "abefcd", "ab*cd");
        testSingle(true, "abefcdgiescdfimde", "ab*cd?i*de");
    }

    private void testSingle(boolean expected, String input, String pattern) {
        assertEquals(expected, isMatch(input, pattern));
    }
}
