import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;

public class FindCommonSymbols extends TestCase {
    private String findCommonSymbols(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return "";
        }

        Set<Character> uniqueSymbols = new HashSet<>();
        for (char c : s1.toCharArray()) {
            uniqueSymbols.add(c);
        }

        StringBuilder result = new StringBuilder();
        for (char c: s2.toCharArray()) {
            if (uniqueSymbols.contains(c)) {
                result.append(c);
            }
        }

        return result.toString();
    }

    public void test() {
        testSingle("asb", "asbd", "asb");
        testSingle("asbdd", "asbd", "asbd");
        testSingle("ddasbdd", "asbd", "asbd");
        testSingle("ddasbdd", null, "");
        testSingle(null, "ddasbdd", "");
    }

    private void testSingle(String s1, String s2, String expected) {
        assertEquals(expected, findCommonSymbols(s1, s2));
    }

}
