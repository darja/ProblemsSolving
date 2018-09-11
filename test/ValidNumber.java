import junit.framework.TestCase;

public class ValidNumber extends TestCase {
    public boolean isNumber(String s) {
        int len = s.length();
        if (len == 0) {
            return false;
        }

        int i = 0;
        while (Character.isSpaceChar(s.charAt(i))) {
            ++i;

            if (i == len) {
                return false;
            }
        }

        boolean hasPoint = false;
        boolean hasE = false;
        boolean hasExp = false;
        boolean hasNumbers = false;
        boolean isEnd = false;

        while (i < len) {
            char c = s.charAt(i);

            if (Character.isSpaceChar(c)) {
                isEnd = true;
                i++;
                continue;
            } else if (isEnd) {
                return false;
            }

            if (c == '.') {
                if (hasPoint || hasE) {
                    return false;
                } else {
                    hasPoint = true;
                }

            } else if (c == 'e' || c == 'E') {
                if (hasE || !hasNumbers) {
                    return false;
                } else {
                    hasE = true;
                }

            } else if (c == '-' || c == '+') {
                if (i > 0) {
                    char prev = s.charAt(i - 1);
                    if (!(Character.isSpaceChar(prev) || prev == 'e' || prev == 'E')) {
                        return false;
                    }
                }

            } else if (!isDigit(c)) {
                return false;

            } else {
                hasNumbers = true;
                if (hasE) {
                    hasExp = true;
                }
            }

            i++;
        }

        return hasNumbers && ((hasE && hasExp) || !hasE);
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public void test() {
        testTrue("4823904");
        testTrue("4");
        testTrue("4e1");
        testTrue("4.1");
        testTrue("   4.1");
        testTrue("   4.6   ");
        testTrue("   4.6e10   ");
        testTrue("   4.6e-10   ");
        testTrue("   -4.6e-10   ");
        testTrue("-5");
        testTrue("   -5");
        testTrue("-500");
        testTrue("-0500");
        testTrue("-00500");
        testTrue("1.e2");
        testTrue(".2");
        testTrue("-.2");
        testTrue("-.2e33");
        testTrue("0e1");
        testTrue("+.9");
        testTrue("+7");

        testFalse("");
        testFalse("   ");
        testFalse("abc");
        testFalse(" 1 d");
        testFalse(" 1 e");
        testFalse("12ee10");
        testFalse("1..2");
        testFalse("1.. 2");
        testFalse("e");
        testFalse("0e");
        testFalse("e4");
        testFalse("e4");
        testFalse("   0e");
        testFalse("   10e");
        testFalse("e.");
        testFalse("-");
        testFalse("+");
        testFalse(".");
        testFalse("  .  ");
        testFalse("  -.  ");
        testFalse(" d -.  ");
    }

    public void testTrue(String input) {
        assertTrue("Success: " + input, isNumber(input));
    }

    public void testFalse(String input) {
        assertFalse("Fail: " + input, isNumber(input));
    }
}
