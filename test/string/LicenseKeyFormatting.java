package string;

import junit.framework.TestCase;

/**
 * @see <a href="https://leetcode.com/problems/license-key-formatting/">Problem Description</a>
 */
@SuppressWarnings("WeakerAccess")
// todo should be improved
public class LicenseKeyFormatting extends TestCase {
    public String licenseKeyFormatting(String S, int K) {
        StringBuilder sb = new StringBuilder();
        int groupSize = 0;
        for (int i = S.length() - 1; i >= 0; --i) {
            char c = S.charAt(i);
            if (c != '-') {
                if (groupSize == K) {
                    groupSize = 0;
                    sb.insert(0, '-');
                }
                sb.insert(0, Character.toUpperCase(c));
                groupSize++;
            }

        }

        return sb.toString();
    }

    private void testOne(String input, int k, String expected) {
        assertEquals(expected, licenseKeyFormatting(input, k));
    }

    public void test() {
        testOne("a", 4, "A");
        testOne("5F3Z-2e-9-w", 4, "5F3Z-2E9W");
        testOne("2-5g-3-J", 2, "2-5G-3J");
        testOne("abcc", 1, "A-B-C-C");
        testOne("--abcc", 1, "A-B-C-C");
        testOne("--a-a-a-a--", 2, "AA-AA");
    }
}
