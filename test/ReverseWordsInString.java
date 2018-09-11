import junit.framework.TestCase;

public class ReverseWordsInString extends TestCase {
    public String reverseWords(String s) {
        char[] is = s.toCharArray();
        int len = is.length;
        reverse(is, 0, len - 1);

        int from = 0;
        int to = 0;

        while (from < len - 1) {
            while (from < (len - 1) && is[from] == ' ') from++;
            to = from;
            while (to < (len - 1) && is[to] != ' ') to++;
            reverse(is, from, to - 1);
            from = to;
        }

        int spacesOffset = 0;
        from = 0;
        to = 0;
        while (from < len - spacesOffset - 1) {
            while (from < len && is[from] == ' ') {
                spacesOffset++;
                from++;
            }
            to = from;

            if (to >= len) {
                break;
            }

            while (to < len - 1 && is[to] != ' ') {
                to++;
            }

            System.arraycopy(is, from, is, from - spacesOffset, to - from);

            for (int i = to - spacesOffset; i < to; ++i) {
                is[i] = ' ';
            }
            from = to - spacesOffset;
        }

        if (is[len - spacesOffset - 1] == ' ') {
            spacesOffset++;
        }

        return new String(is, 0, len - spacesOffset);
    }

    private void reverse(char[] s, int from, int to) {
        char c;
        for (int i = from; i <= (to + from) / 2; ++i) {
            c = s[i];
            s[i] = s[to - i];
            s[to - i] = c;
        }
    }

    public String reverseWords2(String s) {
        StringBuilder word = new StringBuilder();
        StringBuilder result = new StringBuilder();
        boolean isEmpty = true;

        for (char c : s.toCharArray()) {
            if (!Character.isSpaceChar(c)) {
                word.append(c);
            } else if (word.length() > 0) {
                if (isEmpty) {
                    isEmpty = false;
                } else {
                    result.insert(0, " ");
                }
                result.insert(0, word);
                word = new StringBuilder();
            }
        }

        if (word.length() > 0) {
            if (!isEmpty) {
                result.insert(0, " ");
            }

            result.insert(0, word);
        }

        return result.toString();
    }

    public String reverseWords1(String s) {
        String[] parts = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        boolean isEmpty = true;
        for (String part : parts) {
            if (isEmpty) {
                isEmpty = false;
            } else {
                sb.insert(0, " ");
            }
            sb.insert(0, part);
        }

        return sb.toString();
    }

    public void test() {
//        testCase("   ", "");
//        testCase("ab", "ab");
//        testCase("abc", "abc");
//        testCase("abcd", "abcd");
//        testCase("  abcd ", "abcd");
//        testCase("  abcd", "abcd");
//        testCase("abcd  ", "abcd");
//        testCase("   abc ", "abc");
        testCase("   abc", "abc");
        testCase("abc   ", "abc");
        testCase(" abc  def", "def abc");
        testCase(" abc  def iklm   ", "iklm def abc");
    }

    private void testCase(String input, String expectedOutput) {
        assertEquals(expectedOutput, reverseWords(input));
    }
}
