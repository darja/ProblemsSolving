import junit.framework.TestCase;

public class ValidParenthesisString extends TestCase {
    public boolean checkValidString(String s) {
        return back(s, 0, 0, 0);
    }

    private boolean back(String s, int index, int open, int close) {
        if (open < close) {
            return false;
        }

        if (index == s.length()) {
            return open == close;
        }

        char c = s.charAt(index);
        switch (c) {
            case '(': return back(s, index + 1, open + 1, close);
            case ')': return back(s, index + 1, open, close + 1);
            case '*': return
                back(s, index + 1, open + 1, close) ||
                back(s, index + 1, open, close + 1) ||
                back(s, index + 1, open, close);
            default: return back(s, index + 1, open, close);
        }
    }

    public void testTrue(String input) {
        assertTrue("Success: " + input, checkValidString(input));
    }

    public void testFalse(String input) {
        assertFalse("Fail: " + input, checkValidString(input));
    }

    public void test() {
        testTrue("(())");
        testTrue("()()");
        testTrue("(*()");
        testTrue("(*))");
        testTrue("(*)");

        testFalse("(*)))");
        testFalse("()))");
    }
}
