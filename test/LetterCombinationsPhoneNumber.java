import junit.framework.TestCase;

import java.util.LinkedList;
import java.util.List;

public class LetterCombinationsPhoneNumber extends TestCase {
    public List<String> letterCombinations(String digits) {
        List<String> variants = new LinkedList<>();

        if (digits == null || digits.isEmpty()) {
            return variants;
        }

        String[] codes = new String[] {
            "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
        };

        int len = digits.length();
        long maxCode = (long)Math.pow(4, len);
        char[] value = new char[len];

        int index;
        int codeIndex;
        boolean isValidVariant;
        for (int c = 0; c < maxCode; ++c) {
            isValidVariant = true;

            int code = c;
            for (int i = 0; i < len; ++i) {
                index = code % 4;
                codeIndex = digits.charAt(i) - '0';
                if (index >= codes[codeIndex].length()) {
                    isValidVariant = false;
                    break;
                }
                value[i] = codes[codeIndex].charAt(index);

                code /= 4;
            }

            if (isValidVariant) {
                variants.add(new String(value));
            }
        }

        return variants;
    }

    public void test() {
        testSingle("23", new String[] {"ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"});
        testSingle("12", new String[] {"1a", "1b", "1c"});
        testSingle("11", new String[] {"11"});
        testSingle("01", new String[] {"01"});
        testSingle("", new String[0]);
    }

    private void testSingle(String input, String[] expected) {
        List<String> actual = letterCombinations(input);

        assertEquals(actual.size(), expected.length);

        for (String s: expected) {
            assertTrue(actual.contains(s));
        }
    }
}
