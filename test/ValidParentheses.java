import junit.framework.TestCase;

import java.util.LinkedList;

public class ValidParentheses extends TestCase {
    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            switch (c) {
                case '(':
                case '[':
                case '{':
                    stack.push(c);
                    break;

                case ')':
                    if (!doesLastElementMatch(stack, '(')) {
                        return false;
                    }
                    break;

                case '}':
                    if (!doesLastElementMatch(stack, '{')) {
                        return false;
                    }
                    break;

                case ']':
                    if (!doesLastElementMatch(stack, '[')) {
                        return false;
                    }
                    break;


            }
        }

        return (stack.isEmpty());
    }

    private boolean doesLastElementMatch(LinkedList<Character> stack, char expected) {
        return !stack.isEmpty() && stack.pop() == expected;
    }

    private void testFail(String input) {
        assertFalse(isValid(input));
    }

    private void testOk(String input) {
        assertTrue(isValid(input));
    }

    public void test() {
        testFail("[}");
        testFail("())");
        testFail("(()");
        testFail("(()}");
        testFail("(({)}");
        testFail("({)}");
        testFail("{}()[}]");

        testOk("[]");
        testOk("[[]]");
        testOk("[()]");
        testOk("[]{}()");
        testOk("[][{}]()");
        testOk("[][{()}]()");
        testOk("[][{()}]([])");
    }
}
