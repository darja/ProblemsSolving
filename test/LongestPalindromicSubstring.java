import junit.framework.TestCase;

public class LongestPalindromicSubstring extends TestCase{
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len <= 1) {
            return s;
        }

        SubstringPosition maxPos = s.charAt(0) == s.charAt(1) ? findPalindromeWithCenter(s, 0, 1) : findPalindromeWithCenter(s, 0, 0);

        for (int i = 1; i < len - 1; ++i) {
            SubstringPosition p = findPalindromeWithCenter(s, i, i);
            if (p.isLongerThan(maxPos)) {
                maxPos = p;
            }

            if (i < len && s.charAt(i) == s.charAt(i + 1)) {
                p = findPalindromeWithCenter(s, i, i + 1);
                if (p.isLongerThan(maxPos)) {
                    maxPos = p;
                }
            }
        }

        return s.substring(maxPos.from, maxPos.to + 1);
    }

    private SubstringPosition findPalindromeWithCenter(String s, int foundFrom, int foundTo) {
        if (foundFrom > 0 && foundTo < s.length() - 1 && s.charAt(foundFrom - 1) == s.charAt(foundTo + 1)) {
            return findPalindromeWithCenter(s, foundFrom - 1, foundTo + 1);
        }
        return new SubstringPosition(foundFrom, foundTo);
    }

    private class SubstringPosition {
        final int from;
        final int to;

        private SubstringPosition(int from, int to) {
            this.from = from;
            this.to = to;
        }

        public boolean isLongerThan(SubstringPosition s) {
            return to - from > s.to - s.from;
        }
    }

    private void testCase(String input, String output) {
        assertEquals(output, longestPalindrome(input));
    }

    public void test() {
        testCase("ababd", "aba");
        testCase("abbd", "bb");
        testCase("abbad", "abba");
        testCase("fdsabbad", "abba");
        testCase("fddabbad", "dabbad");
        testCase("d", "d");
        testCase("", "");
        testCase("xa", "x");
        testCase("xx", "xx");
    }
}
