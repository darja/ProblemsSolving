import junit.framework.TestCase;

import java.util.HashMap;

@SuppressWarnings("WeakerAccess")
public class LongestSubstringWithAtMostKDistinctCharacters extends TestCase {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null) return 0;

        if (s.length() <= k) {
            return s.length();
        }

        HashMap<Character, Integer> code = new HashMap<>();
        int maxLen = 0;
        int len = 0;

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (code.containsKey(c)) {
                code.put(c, code.get(c) + 1);
            } else {
                code.put(c, 1);
                while (code.size() > k) {
                    char start = s.charAt(i - len);
                    int count = code.get(start);
                    if (count == 1) {
                        code.remove(start);
                    } else {
                        code.put(start, count - 1);
                    }
                    len--;
                }
            }
            len++;
            if (len > maxLen) {
                maxLen = len;
            }
        }
        return maxLen;
    }

    private void testOne(String s, int k, int expected) {
        assertEquals(expected, lengthOfLongestSubstringKDistinct(s, k));
    }

    public void testTrivial() {
        testOne(null, 0, 0);
        testOne("abc", 4, 3);
        testOne("abc", 3, 3);
        testOne("abc", 30, 3);
    }

    public void test() {
        testOne("abc", 2, 2);
        testOne("aabbac", 2, 5);
        testOne("aabbc", 2, 4);
        testOne("aabbccbcc", 2, 7);
        testOne("eceba", 2, 3);
        testOne("aaaaaa", 3, 6);
    }
}
