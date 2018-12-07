package string;

import junit.framework.TestCase;

/**
 * @see <a href="https://leetcode.com/problems/longest-common-prefix/">Problem Description</a>
 */
@SuppressWarnings("WeakerAccess")
// todo can be improved
public class LongestCommonPrefix extends TestCase {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        int i = 0;

        while (true) {
            if (strs[0].length() == i) {
                break;
            }

            char c = strs[0].charAt(i);

            for (int j = 1; j < strs.length; ++j) {
                String str = strs[j];
                if (str.length() == i || str.charAt(i) != c) {
                    return sb.toString();
                }
            }
            sb.append(c);
            ++i;
        }
        return sb.toString();
    }

    public void test() {
        testSingle("", new String[0]);
        testSingle("aaa", new String[] {"aaa"});
        testSingle("a", new String[] {"a"});
        testSingle("aaa", new String[] {"aaa", "aaab"});
        testSingle("aaa", new String[] {"aaac", "aaab"});
        testSingle("a", new String[] {"aaac", "aaab", "abc"});
        testSingle("", new String[] {"aaa", "b", "bc"});
    }

    private void testSingle(String expected, String[] input) {
        assertEquals(expected, longestCommonPrefix(input));
    }
}
