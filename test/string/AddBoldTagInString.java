package string;

import junit.framework.TestCase;

/**
 * @see <a href="https://leetcode.com/problems/add-bold-tag-in-string">Problem Description</a>
 */
@SuppressWarnings("WeakerAccess")
public class AddBoldTagInString extends TestCase {
    public String addBoldTag(String s, String[] dict) {
        boolean[] bold = new boolean[s.length()];

        for (String item : dict) {
            int from = -1;

            do {
                from = s.indexOf(item, from + 1);

                if (from >= 0) {
                    for (int i = 0; i < item.length(); ++i) {
                        bold[from + i] = true;
                    }
                }

            } while (from >= 0);
        }

        StringBuilder sb = new StringBuilder();
        boolean isBold = false;
        for (int i = 0; i < s.length(); ++i) {
            if (bold[i] != isBold) {
                if (bold[i]) {
                    sb.append("<b>");
                } else {
                    sb.append("</b>");
                }
                isBold = bold[i];
            }

            sb.append(s.charAt(i));
        }
        if (isBold) {
            sb.append("</b>");
        }

        return sb.toString();
    }

    private void testOne(String s, String[] dict, String expected) {
        assertEquals(expected, addBoldTag(s, dict));
    }

    public void testTrivial() {
        testOne("", new String[0], "");
        testOne("", new String[] {"aaaa", "bbbb"}, "");
    }

    public void testNotChanged() {
        testOne("a", new String[0], "a");
        testOne("aaa", new String[] {"bbb"}, "aaa");
        testOne("aabb", new String[] {"bbb"}, "aabb");
        testOne("aaa", new String[] {"bbb", "c"}, "aaa");
    }

    public void testChanged() {
        testOne("aaaaabbbc", new String[] {"aa", "b"}, "<b>aaaaabbb</b>c");
        testOne("aaaaabbb", new String[] {"aa", "b"}, "<b>aaaaabbb</b>");
        testOne("aaaaabbb", new String[] {"aa", "bbbbb"}, "<b>aaaaa</b>bbb");
        testOne("aaaabcdda", new String[] {"aaa", "abc"}, "<b>aaaabc</b>dda");
        testOne("aaabbcc", new String[] {"aaa","aab","bc"}, "<b>aaabbc</b>c");
        testOne("abcxyz123", new String[] {"abc","123"}, "<b>abc</b>xyz<b>123</b>");
    }
}
