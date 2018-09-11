import junit.framework.TestCase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LengthOfLastWord extends TestCase {
    public void test() {
        testSingle("Hello, World", 5);
        testSingle("Hello, World ", 5);
        testSingle("Hello, World   ", 5);
        testSingle("Hello", 5);
        testSingle("Hello    ", 5);
        testSingle("   Hello    ", 5);
        testSingle(null, 0);
        testSingle("", 0);
        testSingle("  ", 0);
    }


    public void testSingle(String input, int expected) {
        assertEquals(expected, lengthOfLastWord(input));
    }

    public int lengthOfLastWord(String s) {
        if (s == null) {
            return 0;
        }

        int lastLength = 0;
        int length = 0;

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == ' ' || c == '\n' || c == '\t') {
                if (length != 0) {
                    lastLength = length;
                    length = 0;
                }
            } else {
                length++;
            }
        }
        if (length != 0) {
            return length;
        } else {
            return lastLength;
        }
    }

    public int lengthOfLastWord1(String s) {
        if (s == null) {
            return 0;
        }

        Pattern p = Pattern.compile("(\\S+)\\s*$");

        Matcher m = p.matcher(s);
        if (m.find()) {
            String lastWord = m.group(1);
            return lastWord.length();
        }

        return 0;
    }
}
